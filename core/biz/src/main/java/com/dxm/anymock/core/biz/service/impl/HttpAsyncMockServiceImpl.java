package com.dxm.anymock.core.biz.service.impl;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.base.exception.SysException;
import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.common.dal.model.enums.ConfigMode;
import com.dxm.anymock.core.biz.Delayer;
import com.dxm.anymock.core.biz.service.GroovyService;
import com.dxm.anymock.core.biz.service.HttpAsyncMockService;
import com.dxm.anymock.core.biz.HttpMockContext;
import groovy.lang.Binding;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import static com.dxm.anymock.common.dal.model.enums.ConfigMode.GROOVY;
import static com.dxm.anymock.common.dal.model.enums.ConfigMode.GROOVY_TEMPLATE_SWITCH_CASE;
import static com.dxm.anymock.common.dal.model.enums.ConfigMode.TEXT;

@Service
public class HttpAsyncMockServiceImpl implements HttpAsyncMockService {

    private static final Logger logger = LoggerFactory.getLogger(HttpAsyncMockServiceImpl.class);

    @Autowired
    private GroovyService groovyService;

    private Binding buildBindig(HttpServletRequest request, HttpURLConnection httpURLConnection) {
        Binding binding = new Binding();
        binding.setProperty("request", request);
        binding.setProperty("httpURLConnection", httpURLConnection);
        return binding;
    }

    @Override
    public void mock(HttpMockContext context, HttpServletRequest request) throws Exception {
        HttpInterfaceBO httpInterfaceBO = context.getHttpInterfaceBO();

        // 异步延迟
        Delayer.delay(httpInterfaceBO.getAsyncDelay());
        logger.info("Async delay finished");

        // 初始化HttpUrlConnection
        URL url = new URL(httpInterfaceBO.getCallbackRequestUrl());
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestMethod(httpInterfaceBO.getCallbackRequestMethod());

        httpInterfaceBO.getCallbackRequestHeaderList().forEach(httpHeader
                -> httpURLConnection.setRequestProperty(httpHeader.getName(), httpHeader.getValue()));

        ConfigMode configMode = httpInterfaceBO.getConfigMode();

        String requestContent;
        if (configMode == TEXT) {
            requestContent = httpInterfaceBO.getCallbackRequestBody();
        } else if (configMode == GROOVY) {
            requestContent = groovyService.exec(
                    buildBindig(request, httpURLConnection),
                    httpInterfaceBO.getAsyncScript());
        } else if (configMode == GROOVY_TEMPLATE_SWITCH_CASE) {
            requestContent = groovyService.exec(
                    buildBindig(request, httpURLConnection),
                    context.getHttpInterfaceBranchBO().getAsyncScript());
        } else {
            throw new SysException("Unknown ConfigMode");
        }

        logger.info("RequestContent = {}", requestContent);
        if (StringUtils.isNotBlank(requestContent)) {
            if (HttpMethod.GET.matches(httpInterfaceBO.getCallbackRequestMethod())) {
                // rewrite url
                try {
                    Field field = URLConnection.class.getDeclaredField("url");
                    field.setAccessible(true);
                    field.set(httpURLConnection,
                            new URL(String.format("%s?%s", httpInterfaceBO.getCallbackRequestUrl(), requestContent)));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new BizException(ResultCode.UNEXPECTED_ERROR);
                }
            } else {
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(requestContent.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        }else {
            logger.info("Async RequestContent 为空");

            return;
        }

        int responseCode = httpURLConnection.getResponseCode();
        logger.info("ResponseCode = {}", responseCode);

        String responseContent = IOUtils.toString(httpURLConnection.getInputStream(), StandardCharsets.UTF_8);
        logger.info("ResponseContent = {}", responseContent);
    }
}
