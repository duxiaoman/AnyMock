package com.dxm.anymock.manager.biz.security;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        UserRole userRole;
        if (StringUtils.equals(request.getHeader("User-Agent"), "A9E62132FE717B74F504A291F96D3F1B")) {
            userRole = UserRole.ADMIN;
        } else {
            userRole = UserRole.GUEST;
        }
        logger.info("UserRole = {}", userRole.name());
        SecurityContextHolder.setUserRole(userRole);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        SecurityContextHolder.setUserRole(null);
    }
}
