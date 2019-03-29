package com.dxm.anymock.core.biz;

import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.common.dal.model.HttpInterfaceBranchBO;
import groovy.lang.Binding;

public class HttpMockContext {
    private HttpInterfaceBO httpInterfaceBO;
    private HttpInterfaceBranchBO httpInterfaceBranchBO;

    public HttpInterfaceBO getHttpInterfaceBO() {
        return httpInterfaceBO;
    }

    public void setHttpInterfaceBO(HttpInterfaceBO httpInterfaceBO) {
        this.httpInterfaceBO = httpInterfaceBO;
    }

    public HttpInterfaceBranchBO getHttpInterfaceBranchBO() {
        return httpInterfaceBranchBO;
    }

    public void setHttpInterfaceBranchBO(HttpInterfaceBranchBO httpInterfaceBranchBO) {
        this.httpInterfaceBranchBO = httpInterfaceBranchBO;
    }
}
