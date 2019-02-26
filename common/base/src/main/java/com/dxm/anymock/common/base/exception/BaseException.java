package com.dxm.anymock.common.base.exception;

import com.dxm.anymock.common.base.enums.ErrorCode;

public class BaseException extends RuntimeException {
    private ErrorCode errorCode;
    private String msg;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, Throwable e) {
        super(errorCode.name(), e);
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, String msg) {
        super(String.format("%s(%s)", errorCode.name(), msg));
        this.errorCode = errorCode;
    }
}
