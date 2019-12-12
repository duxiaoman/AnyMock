package com.dxm.anymock.manager.biz.model.response.dto;

public class FieldErrorDTO {
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
}
