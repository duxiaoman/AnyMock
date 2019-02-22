package com.dxm.anymock.web.service.api;

import com.alibaba.fastjson.JSONObject;

public class BaseResponse {
    private String resultCode;

    private String resultMsg;

    private Object data;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BaseResponse() {
        this.resultCode = "000000";
        this.resultMsg = "";
        this.data = null;
    }

    public BaseResponse(Object data) {
        this.resultCode = "000000";
        this.resultMsg = "";
        this.data = data;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
