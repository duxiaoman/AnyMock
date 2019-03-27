package com.dxm.anymock.manager.biz.model.request;

import com.dxm.anymock.common.dal.model.HttpInterfaceKeyBO;

import javax.validation.constraints.NotNull;

public class HttpInterfaceConflictDetectionRequest extends HttpInterfaceKeyBO {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
