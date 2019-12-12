package com.dxm.anymock.common.base.model.response;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.enums.ResultCode;

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

    public BaseResponse() {
        this.resultCode = ResultCode.SUCCESS.getCode();
        this.resultMsg = "";
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
