package com.dxm.anymock.common.base.entity;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.check.CommonDeleteCheck;
import com.dxm.anymock.common.base.check.CommonInsertCheck;
import com.dxm.anymock.common.base.check.CommonUpdateCheck;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Space {
    @NotNull(groups = {CommonUpdateCheck.class, CommonDeleteCheck.class})
    private Long id;

    @NotBlank(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String label;

    @NotNull(groups = {CommonInsertCheck.class})
    private Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
