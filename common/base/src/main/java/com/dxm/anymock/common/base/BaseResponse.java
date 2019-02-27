package com.dxm.anymock.common.base;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.enums.ErrorCode;

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

    public static BaseResponse success() {
        return BaseResponse.success(null, "");
    }

    public static BaseResponse success(Object data) {
        return BaseResponse.success(data, "");
    }

    public static BaseResponse success(String msg) {
        return BaseResponse.success(null, msg);
    }

    public static BaseResponse success(Object data, String msg) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResultCode(ErrorCode.SUCCESS.getCode());
        baseResponse.setResultMsg(msg);
        return baseResponse;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
