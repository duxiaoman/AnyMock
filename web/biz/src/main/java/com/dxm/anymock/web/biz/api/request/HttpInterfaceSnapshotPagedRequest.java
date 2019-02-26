package com.dxm.anymock.web.biz.api.request;

import javax.validation.constraints.NotNull;

public class HttpInterfaceSnapshotPagedRequest extends BasePagedRequest {
    @NotNull
    private Long httpInterfaceId;

    public Long getHttpInterfaceId() {
        return httpInterfaceId;
    }

    public void setHttpInterfaceId(Long httpInterfaceId) {
        this.httpInterfaceId = httpInterfaceId;
    }
}
