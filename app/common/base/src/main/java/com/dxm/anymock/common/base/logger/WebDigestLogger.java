package com.dxm.anymock.common.base.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebDigestLogger extends HandlerInterceptorAdapter {

    private Logger logger;

    public WebDigestLogger(String loggerName) {
        logger = LoggerFactory.getLogger(loggerName);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("{} {}", request.getMethod(), request.getRequestURI());
        return true;
    }
}
