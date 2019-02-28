package com.dxm.anymock.core.biz.impl;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.core.biz.GroovyService;
import com.dxm.anymock.core.biz.HttpAsyncMockService;
import com.dxm.anymock.core.biz.entity.HttpMockContext;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

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

    private void mockAsyncDelay(HttpMockContext httpMockContext) throws InterruptedException {
        HttpInterface httpInterface = httpMockContext.getHttpInterface();
        if (httpInterface.getAsyncDelay() > GlobalConstant.MAX_ASYNC_DELAY) {
            throw new BaseException(ErrorCode.ASYNC_DELAY_TOO_LARGE);
        }
        if (httpInterface.getAsyncDelay() > 0) {
            Thread.sleep(httpInterface.getAsyncDelay());
        }
    }

    private void initHttpURLConnection(HttpMockContext httpMockContext) throws IOException {
        HttpInterface httpInterface = httpMockContext.getHttpInterface();
        URL url = new URL(httpInterface.getCallbackRequestUrl());

        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestMethod(httpInterface.getCallbackRequestMethod());

        httpInterface.getCallbackRequestHeaderList()
                .forEach(httpHeader -> httpURLConnection.setRequestProperty(httpHeader.getName(), httpHeader.getValue()));
        httpMockContext.setHttpURLConnection(httpURLConnection);
    }

    private void writeRequestContent(HttpMockContext httpMockContext, String requestContent) throws IOException {
        HttpURLConnection httpURLConnection = httpMockContext.getHttpURLConnection();
        if (StringUtils.isNotBlank(requestContent)) {
            if (HttpMethod.GET.matches("GET")) {
                // rewrite url
                try {
                    Field field = URLConnection.class.getDeclaredField("url");
                    field.setAccessible(true);

                    field.set(httpURLConnection, new URL(String.format("%s?%s",
                            httpMockContext.getHttpInterface().getCallbackRequestUrl(), requestContent)));
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
    public void mock(HttpMockContext httpMockContext) throws Exception {
        logger.info("HttpAsyncMockService start");
        mockAsyncDelay(httpMockContext);
        initHttpURLConnection(httpMockContext);

        HttpInterface httpInterface = httpMockContext.getHttpInterface();
        String requestContent;
        switch (httpMockContext.getConfigMode()) {
            case STATIC:
                requestContent = httpInterface.getCallbackRequestBody();
                break;
            case SCRIPT:
                groovyService.bindAsyncProperty(httpMockContext);
                requestContent = groovyService.exec(httpMockContext, httpInterface.getAsyncScript());
                break;
            case SCRIPT_WITH_BRANCH:
                groovyService.bindAsyncProperty(httpMockContext);
                requestContent = groovyService.exec(httpMockContext, httpMockContext.getBranchScript().getAsyncScript());
                break;
            default:
                throw new BaseException(ErrorCode.UNKNOWN_CONFIG_MODE);
        }
        logger.info("RequestContent = {}", requestContent);
        writeRequestContent(httpMockContext, requestContent);

        int responseCode = httpMockContext.getHttpURLConnection().getResponseCode();
        logger.info("ResponseCode = {}", responseCode);

        String responseContent = IOUtils.toString(httpMockContext.getHttpURLConnection().getInputStream(), StandardCharsets.UTF_8);
        logger.info("ResponseContent = {}", responseContent);
    }
}
