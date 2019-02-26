package com.dxm.anymock.common.base.entity;

import com.alibaba.fastjson.JSONObject;

public class FieldError {
    private String field;
    private String errorInfo;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
