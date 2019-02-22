package com.dxm.anymock.common.base.entity;

public class RequestType {
    private String method;
    private String uri;

    public RequestType(HttpInterface httpInterface) {
        this.method = httpInterface.getRequestMethod();
        this.uri = httpInterface.getRequestUri();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
