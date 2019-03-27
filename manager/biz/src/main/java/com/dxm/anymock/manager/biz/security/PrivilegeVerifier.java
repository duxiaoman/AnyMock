package com.dxm.anymock.manager.biz.security;

import com.dxm.anymock.common.dal.model.enums.AccessAuthority;

public class PrivilegeVerifier {
    public static boolean hasPermission(AccessAuthority accessAuthority) {
        return !(accessAuthority != AccessAuthority.PUBLIC && SecurityContextHolder.getUserRole() != UserRole.ADMIN);
    }
}
