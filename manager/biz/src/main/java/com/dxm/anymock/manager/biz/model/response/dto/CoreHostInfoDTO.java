package com.dxm.anymock.manager.biz.model.response.dto;

public class CoreHostInfoDTO {
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
}
