package com.dxm.anymock.common.base.exception;

import com.dxm.anymock.common.base.enums.ResultCode;

public class BizException extends RuntimeException {
    private ResultCode resultCode;

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public BizException(ResultCode resultCode) {
        super(resultCode.name());
        this.resultCode = resultCode;
    }

    public BizException(ResultCode resultCode, Throwable e) {
        super(resultCode.name(), e);
        this.resultCode = resultCode;
    }

    /*public BizException(ResultCode resultCode, String msg) {
        super(String.format("%s(%s)", resultCode.name(), msg));
        this.resultCode = resultCode;
    }*/
}
