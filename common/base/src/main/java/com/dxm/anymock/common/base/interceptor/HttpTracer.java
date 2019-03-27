package com.dxm.anymock.common.base.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpTracer extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HttpTracer.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("{} {} {}", request.getRemoteAddr(), request.getMethod(), request.getRequestURI());
        return true;
    }
}
