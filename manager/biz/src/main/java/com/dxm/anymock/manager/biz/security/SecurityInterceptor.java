package com.dxm.anymock.manager.biz.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

    private String password;

    public SecurityInterceptor(String password) {
        this.password = password;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        UserRole userRole;
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null && userAgent.contains(password)) {
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
