package com.dxm.anymock.common.dal.entity;

public class HttpInterfaceBranchScriptPO {
    private Long id;

    private Long httpInterfaceId;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHttpInterfaceId() {
        return httpInterfaceId;
    }

    public void setHttpInterfaceId(Long httpInterfaceId) {
        this.httpInterfaceId = httpInterfaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}