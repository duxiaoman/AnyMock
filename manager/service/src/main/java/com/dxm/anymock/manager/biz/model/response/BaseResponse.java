package com.dxm.anymock.manager.biz.model.response;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class BaseResponse {
    private String resultCode;

    private String resultMsg;

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

    public BaseResponse(Map<String, String> resultMap) {
        this.resultCode = resultMap.get("resultCode");
        this.resultMsg = resultMap.get("resultMsg");
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
