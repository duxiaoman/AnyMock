package com.dxm.anymock.manager.biz.security;

public class SecurityContextHolder {
    private static final ThreadLocal<UserRole> userRole = new ThreadLocal<>();

    public static void setUserRole(UserRole userRole) {
        SecurityContextHolder.userRole.set(userRole);
    }

    public static UserRole getUserRole() {
        return SecurityContextHolder.userRole.get();
    }

    public static String getUsername() {
        return SecurityContextHolder.userRole.get().name();
    }
}
