package com.dxm.anymock.manager.biz.model.request;

import com.dxm.anymock.common.dal.model.HttpInterfaceKeyBO;

import javax.validation.constraints.NotNull;

public class HttpInterfaceConflictDetectionRequest {
    @NotNull
    private Long id;

    @NotNull
    private String uri;

    @NotNull
    private String method;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
