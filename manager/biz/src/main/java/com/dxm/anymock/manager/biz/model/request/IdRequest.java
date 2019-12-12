package com.dxm.anymock.manager.biz.model.request;

import javax.validation.constraints.NotNull;

public class IdRequest {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
