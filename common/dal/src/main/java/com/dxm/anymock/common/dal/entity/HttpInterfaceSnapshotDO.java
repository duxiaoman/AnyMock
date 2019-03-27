package com.dxm.anymock.common.dal.entity;

import java.util.Date;

public class HttpInterfaceSnapshotDO {
    private Long id;

    private Long httpInterfaceId;

    private String httpInterfaceRequestUri;

    private String httpInterfaceRequestMethod;

    private Long httpInterfaceSpaceId;

    private String httpInterface;

    private String opType;

    private Date gmtCreate;

    private Date gmtModified;

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

    public String getHttpInterfaceRequestUri() {
        return httpInterfaceRequestUri;
    }

    public void setHttpInterfaceRequestUri(String httpInterfaceRequestUri) {
        this.httpInterfaceRequestUri = httpInterfaceRequestUri == null ? null : httpInterfaceRequestUri.trim();
    }

    public String getHttpInterfaceRequestMethod() {
        return httpInterfaceRequestMethod;
    }

    public void setHttpInterfaceRequestMethod(String httpInterfaceRequestMethod) {
        this.httpInterfaceRequestMethod = httpInterfaceRequestMethod == null ? null : httpInterfaceRequestMethod.trim();
    }

    public Long getHttpInterfaceSpaceId() {
        return httpInterfaceSpaceId;
    }

    public void setHttpInterfaceSpaceId(Long httpInterfaceSpaceId) {
        this.httpInterfaceSpaceId = httpInterfaceSpaceId;
    }

    public String getHttpInterface() {
        return httpInterface;
    }

    public void setHttpInterface(String httpInterface) {
        this.httpInterface = httpInterface == null ? null : httpInterface.trim();
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType == null ? null : opType.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}