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
import com.dxm.anymock.core.biz.entity.MockContext;
import groovy.lang.GroovyClassLoader;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@Service
public class HttpMockServiceImpl implements HttpMockService {

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


    private MockContext initMockContext(HttpServletRequest request, HttpServletResponse response) {
        RequestType requestType = buildRequestType(request);
        HttpInterface httpInterface = loadHttpInterface(requestType);
        if (BooleanUtils.isNotTrue(httpInterface.getStart())) {
            throw new BaseException(ErrorCode.HTTP_INTERFACE_NOT_STARTED);
        }

        ConfigMode configMode = CodeBasedEnumUtil.convertByCode(ConfigMode.class, httpInterface.getConfigMode());
        if (configMode == null) {
            throw new BaseException(ErrorCode.UNKNOWN_CONFIG_MODE);
        }

        MockContext mockContext = new MockContext();
        mockContext.setRequestType(requestType);
        mockContext.setHttpInterface(httpInterface);
        mockContext.setHttpInterface(httpInterface);
        mockContext.setHttpServletRequest(request);
        mockContext.setHttpServletResponse(response);
        mockContext.setConfigMode(configMode);
        return mockContext;
    }

    private void logRequestMsg(HttpServletRequest request) {
        if (logger.isInfoEnabled()) {
            StringBuilder stringBuilder = new StringBuilder();
            String banner = String.format("\n######## HTTP REQUEST FROM [%15s] ########\n", request.getRemoteAddr());
            stringBuilder.append(banner);
            stringBuilder.append(request.getMethod());
            stringBuilder.append(" ");
            stringBuilder.append(request.getRequestURI());
            if (request.getQueryString() != null) {
                stringBuilder.append("?");
                stringBuilder.append(request.getQueryString());
            }
            stringBuilder.append("\n");

            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String)headerNames.nextElement();
                stringBuilder.append(key);
                stringBuilder.append(":");
                stringBuilder.append(request.getHeader(key));
                stringBuilder.append("\n");
            }
            stringBuilder.append("\n");

            String body = (String)request.getAttribute("body");
            if (StringUtils.isNotBlank(body)) {
                stringBuilder.append(body);
            } else {
                boolean first = true;
                Map<String, String[]> parameterMap = request.getParameterMap();
                for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                    for (String value : entry.getValue()) {
                        if (first) {
                            first = false;
                        } else {
                            stringBuilder.append("&");
                        }
                        stringBuilder.append(entry.getKey());
                        stringBuilder.append("=");
                        stringBuilder.append(value);
                    }
                }
            }
            stringBuilder.append("\n#####################################################");
            logger.info(stringBuilder.toString());
        }
    }

    @Override
    public void mock(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("body", IOUtils.toString(request.getInputStream(), request.getCharacterEncoding()));
        logRequestMsg(request);

        MockContext mockContext = initMockContext(request, response);
        httpSyncMockService.mock(mockContext);
        if (BooleanUtils.isTrue(mockContext.getHttpInterface().getNeedAsyncCallback())) {
            threadPoolTaskExecutor.execute(() -> {
                try {
                    httpAsyncMockService.mock(mockContext);
                } catch (Exception e) {
                    logger.warn("", e);
                }
            });
        }
    }

    private RequestType buildRequestType(HttpServletRequest request) {
        RequestType requestType = new RequestType();
        requestType.setMethod(request.getMethod());
        requestType.setUri(request.getRequestURI());
        return requestType;
    }

    private HttpInterface loadHttpInterface(RequestType requestType) {
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
        return httpInterface;
    }
}
