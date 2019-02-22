package com.dxm.anymock.common.base.entity;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.check.ValueCheck;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class HttpHeader implements Serializable {

    @NotBlank(groups = {ValueCheck.class})
    private String name;

    @NotNull(groups = {ValueCheck.class})
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
