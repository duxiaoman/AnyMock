package com.dxm.anymock.core.biz.impl;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.core.biz.GroovyService;
import com.dxm.anymock.core.biz.HttpAsyncMockService;
import com.dxm.anymock.core.biz.entity.MockContext;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import sun.net.www.ParseUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@Service
public class HttpAsyncMockServiceImpl implements HttpAsyncMockService {

    private static final Logger logger = LoggerFactory.getLogger(GlobalConstant.MOCK_SERVICE_LOGGER);

    @Autowired
    private GroovyService groovyService;

    private void mockAsyncDelay(MockContext mockContext) throws InterruptedException {
        HttpInterface httpInterface = mockContext.getHttpInterface();
        if (httpInterface.getAsyncDelay() > GlobalConstant.MAX_ASYNC_DELAY) {
            throw new BaseException(ErrorCode.ASYNC_DELAY_TOO_LARGE);
        }
        if (httpInterface.getAsyncDelay() > 0) {
            Thread.sleep(httpInterface.getAsyncDelay());
        }
    }

    private void initHttpURLConnection(MockContext mockContext) throws IOException {
        HttpInterface httpInterface = mockContext.getHttpInterface();
        URL url = new URL(httpInterface.getCallbackRequestUrl());

        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestMethod(httpInterface.getCallbackRequestMethod());

        httpInterface.getCallbackRequestHeaderList()
                .forEach(httpHeader -> httpURLConnection.setRequestProperty(httpHeader.getName(), httpHeader.getValue()));
        mockContext.setHttpURLConnection(httpURLConnection);
    }

    private void writeRequestContent(MockContext mockContext, String requestContent) throws IOException {
        HttpURLConnection httpURLConnection = mockContext.getHttpURLConnection();
        if (StringUtils.isNotBlank(requestContent)) {
            if (HttpMethod.GET.matches("GET")) {
                // rewrite url
                try {
                    Field field = URLConnection.class.getDeclaredField("url");
                    field.setAccessible(true);

                    field.set(httpURLConnection, new URL(String.format("%s?%s",
                            mockContext.getHttpInterface().getCallbackRequestUrl(), requestContent)));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new BaseException(ErrorCode.UNEXPECTED_ERROR);
                }
            } else {
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(requestContent.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    @Override
    public void mock(MockContext mockContext) throws Exception {
        logger.info("HttpAsyncMockService start");
        mockAsyncDelay(mockContext);
        initHttpURLConnection(mockContext);

        HttpInterface httpInterface = mockContext.getHttpInterface();
        String requestContent;
        switch (mockContext.getConfigMode()) {
            case STATIC:
                requestContent = httpInterface.getCallbackRequestBody();
                break;
            case SCRIPT:
                requestContent = groovyService.exec(mockContext, httpInterface.getAsyncScript());
                break;
            case SCRIPT_WITH_BRANCH:
                requestContent = groovyService.exec(mockContext, mockContext.getBranchScript().getAsyncScript());
                break;
            default:
                throw new BaseException(ErrorCode.UNKNOWN_CONFIG_MODE);
        }
        logger.info("RequestContent = {}", requestContent);
        writeRequestContent(mockContext, requestContent);

        int responseCode = mockContext.getHttpURLConnection().getResponseCode();
        logger.info("ResponseCode = {}", responseCode);

        String responseContent = IOUtils.toString(mockContext.getHttpURLConnection().getInputStream(), StandardCharsets.UTF_8);
        logger.info("ResponseContent = {}", responseContent);
    }
}
