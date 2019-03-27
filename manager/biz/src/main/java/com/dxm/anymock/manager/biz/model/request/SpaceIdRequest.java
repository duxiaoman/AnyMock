package com.dxm.anymock.manager.biz.model.request;

import javax.validation.constraints.NotNull;

public class SpaceIdRequest {
    @NotNull
    private Long spaceId;

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }
}
