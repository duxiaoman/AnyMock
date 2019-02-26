package com.dxm.anymock.web.biz.api.request;

import com.alibaba.fastjson.JSONObject;

import javax.validation.constraints.NotNull;

public class HttpInterfacePagedRequest extends BasePagedRequest {
    @NotNull
    private Long spaceId;

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
