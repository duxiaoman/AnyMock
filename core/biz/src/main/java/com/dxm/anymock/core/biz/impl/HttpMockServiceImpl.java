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
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.ProfilerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

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

    private void logRawHttpRequestMsg(MockContext mockContext) {
        if (logger.isInfoEnabled()) {
            logger.info("\n################### HTTP REQUEST ###################\n"
                    + mockContext.getRawHttpRequestMsg()
                    + "\n####################################################");
        }
    }

    private void storeHttpBody(MockContext mockContext) throws IOException {
        HttpServletRequest request = mockContext.getHttpServletRequest();
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

    private void loadHttpInterface(MockContext mockContext) {
        RequestType requestType = new RequestType(mockContext.getHttpServletRequest());

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

        mockContext.setRequestType(requestType);
        mockContext.setHttpInterface(httpInterface);
        mockContext.setConfigMode(configMode);
    }

    @Override
    public void mock(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // init profiler
        Profiler profiler = new Profiler("THREAD-PROFILER");
        profiler.setLogger(logger);
        ProfilerRegistry profilerRegistry = ProfilerRegistry.getThreadContextInstance();
        profiler.registerWith(profilerRegistry);

        profiler.start("init MockContext");
        MockContext mockContext = new MockContext(request, response);

        profiler.start("load httpInterface");
        loadHttpInterface(mockContext);

        profiler.start("store http body");
        storeHttpBody(mockContext);

        profiler.start("log request msg");
        logRawHttpRequestMsg(mockContext);

        profiler.start("sync mock");
        profiler.startNested(HttpSyncMockService.class.getSimpleName());
        httpSyncMockService.mock(mockContext);

        profiler.start("async mock");
        if (BooleanUtils.isTrue(mockContext.getHttpInterface().getNeedAsyncCallback())) {
            threadPoolTaskExecutor.execute(() -> {
                try {
                    httpAsyncMockService.mock(mockContext);
                } catch (Exception e) {
                    logger.warn("", e);
                }
            });
        }
        profiler.stop();
        profiler.log();
    }
}
