package com.dxm.anymock.common.base.entity;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.check.PrimaryKeyCheck;
import com.dxm.anymock.common.base.check.ValueCheck;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Space {
    @NotNull(groups = {PrimaryKeyCheck.class})
    private Long id;

    @NotBlank(groups = {ValueCheck.class})
    private String name;

    private List<SubSpace> subSpaceList;

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

    public List<SubSpace> getSubSpaceList() {
        return subSpaceList;
    }

    public void setSubSpaceList(List<SubSpace> subSpaceList) {
        this.subSpaceList = subSpaceList;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
