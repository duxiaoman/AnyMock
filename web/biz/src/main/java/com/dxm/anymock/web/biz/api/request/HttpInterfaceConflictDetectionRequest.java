package com.dxm.anymock.web.biz.api.request;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.entity.RequestType;

import javax.validation.constraints.NotNull;

public class HttpInterfaceConflictDetectionRequest extends RequestType {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
