package com.dxm.anymock.common.base.entity;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.check.PrimaryKeyCheck;
import com.dxm.anymock.common.base.check.ValueCheck;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SubSpace {

    @NotNull(groups = {PrimaryKeyCheck.class})
    private Long id;

    @NotBlank(groups = {ValueCheck.class})
    private String name;

    @NotNull(groups = {ValueCheck.class})
    private Long spaceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
