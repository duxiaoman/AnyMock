package com.dxm.anymock.web.biz.api.response;

import com.alibaba.fastjson.JSONObject;

public class HostInfo {
    private String host;
    private Integer httpInterfacePort;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getHttpInterfacePort() {
        return httpInterfacePort;
    }

    public void setHttpInterfacePort(Integer httpInterfacePort) {
        this.httpInterfacePort = httpInterfacePort;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
