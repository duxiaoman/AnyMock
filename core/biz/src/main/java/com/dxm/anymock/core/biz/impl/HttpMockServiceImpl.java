package com.dxm.anymock.core.biz.impl;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.RequestType;
import com.dxm.anymock.common.base.enums.CodeBasedEnumUtil;
import com.dxm.anymock.common.base.enums.ConfigMode;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.common.dal.dao.HttpInterfaceDao;
import com.dxm.anymock.common.dal.dao.RedisDao;
import com.dxm.anymock.core.biz.HttpAsyncMockService;
import com.dxm.anymock.core.biz.HttpMockService;
import com.dxm.anymock.core.biz.HttpSyncMockService;
import com.dxm.anymock.core.biz.entity.HttpMockContext;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@Service
public class HttpMockServiceImpl implements HttpMockService {

    private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";

    private static final Logger logger = LoggerFactory.getLogger(GlobalConstant.MOCK_SERVICE_LOGGER);

    @Autowired
    private HttpInterfaceDao httpInterfaceDao;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private HttpSyncMockService httpSyncMockService;

    @Autowired
    private HttpAsyncMockService httpAsyncMockService;

    private void logRawHttpRequestMsg(HttpMockContext httpMockContext) {
        if (logger.isInfoEnabled()) {
            logger.info("\n################### HTTP REQUEST ###################\n"
                    + httpMockContext.getRawHttpRequestMsg()
                    + "\n####################################################");
        }
    }

    private void storeHttpBody(HttpMockContext httpMockContext) throws IOException {
        HttpServletRequest request = httpMockContext.getHttpServletRequest();
        String contentType = request.getContentType();
        String method = request.getMethod();
        String requestEncoding =  request.getCharacterEncoding();
        boolean isFormPost = (contentType != null && contentType.contains(FORM_CONTENT_TYPE) &&
                HttpMethod.POST.matches(method));
        String body;
        if (isFormPost) {
            StringBuilder content = new StringBuilder();
            Map<String, String[]> form = request.getParameterMap();
            for (Iterator<String> nameIterator = form.keySet().iterator(); nameIterator.hasNext(); ) {
                String name = nameIterator.next();
                List<String> values = Arrays.asList(form.get(name));
                for (Iterator<String> valueIterator = values.iterator(); valueIterator.hasNext(); ) {
                    String value = valueIterator.next();
                    content.append(URLEncoder.encode(name, requestEncoding));
                    if (value != null) {
                        content.append('=');
                        content.append(URLEncoder.encode(value, requestEncoding));
                        if (valueIterator.hasNext()) {
                            content.append('&');
                        }
                    }
                }
                if (nameIterator.hasNext()) {
                    content.append('&');
                }
            }
            body = content.toString();
        } else {
            body = IOUtils.toString(request.getInputStream(), requestEncoding);
        }
        request.setAttribute("body", body);
    }

    private void loadHttpInterface(HttpMockContext httpMockContext) {
        RequestType requestType = new RequestType(httpMockContext.getHttpServletRequest());

        logger.info("Loading HTTP interface from redis...");
        HttpInterface httpInterface = redisDao.getHttpInterface(requestType);
        if (httpInterface == null) {
            logger.info("Loading HTTP interface from DB...");
            httpInterface = httpInterfaceDao.selectByRequestType(requestType);
            if (httpInterface == null) {
                throw new BaseException(ErrorCode.HTTP_INTERFACE_NOT_FOUND);
            }
            redisDao.setHttpInterface(requestType, httpInterface);
        }

        if (BooleanUtils.isNotTrue(httpInterface.getStart())) {
            throw new BaseException(ErrorCode.HTTP_INTERFACE_NOT_STARTED);
        }

        // check config mode
        ConfigMode configMode = CodeBasedEnumUtil.convertByCode(ConfigMode.class, httpInterface.getConfigMode());
        if (configMode == null) {
            throw new BaseException(ErrorCode.UNKNOWN_CONFIG_MODE);
        }

        httpMockContext.setRequestType(requestType);
        httpMockContext.setHttpInterface(httpInterface);
        httpMockContext.setConfigMode(configMode);
    }

    @Override
    public void mock(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpMockContext httpMockContext = new HttpMockContext(request, response);
        loadHttpInterface(httpMockContext);
        storeHttpBody(httpMockContext);
        logRawHttpRequestMsg(httpMockContext);
        httpSyncMockService.mock(httpMockContext);

        if (BooleanUtils.isTrue(httpMockContext.getHttpInterface().getNeedAsyncCallback())) {
            threadPoolTaskExecutor.execute(() -> {
                try {
                    httpAsyncMockService.mock(httpMockContext);
                } catch (Exception e) {
                    logger.warn("", e);
                }
            });
        }
    }
}
