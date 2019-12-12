package com.dxm.anymock.core.biz;

import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.common.dal.model.HttpInterfaceBranchBO;
import com.dxm.anymock.common.dal.model.InterfaceBranchBO;
import groovy.lang.Binding;

public class HttpMockContext {
    private HttpInterfaceBO httpInterfaceBO;

    private InterfaceBranchBO InterfaceBranchBO;

    public HttpInterfaceBO getHttpInterfaceBO() {
        return httpInterfaceBO;
    }

    public void setHttpInterfaceBO(HttpInterfaceBO httpInterfaceBO) {
        this.httpInterfaceBO = httpInterfaceBO;
    }

    public com.dxm.anymock.common.dal.model.InterfaceBranchBO getInterfaceBranchBO() {
        return InterfaceBranchBO;
    }

    public void setInterfaceBranchBO(com.dxm.anymock.common.dal.model.InterfaceBranchBO interfaceBranchBO) {
        InterfaceBranchBO = interfaceBranchBO;
    }
}
