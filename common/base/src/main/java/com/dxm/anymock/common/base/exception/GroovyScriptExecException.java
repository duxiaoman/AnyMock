package com.dxm.anymock.common.base.exception;

public class GroovyScriptExecException extends RuntimeException {
    private String rawHttpRequestMsg;

    public GroovyScriptExecException(String rawHttpRequestMsg, Throwable e) {
        super(e);
        this.rawHttpRequestMsg = rawHttpRequestMsg;
    }

    public String getRawHttpRequestMsg() {
        return rawHttpRequestMsg;
    }

    public void setRawHttpRequestMsg(String rawHttpRequestMsg) {
        this.rawHttpRequestMsg = rawHttpRequestMsg;
    }
}
