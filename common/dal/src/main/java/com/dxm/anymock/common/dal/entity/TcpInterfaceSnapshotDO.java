package com.dxm.anymock.common.dal.entity;

import java.util.Date;

public class TcpInterfaceSnapshotDO {
    private Long id;

    private Long tcpInterfaceId;

    private String tcpInterfaceRequestUri;

    private Long tcpInterfaceSpaceId;

    private String tcpInterface;

    private String opType;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTcpInterfaceId() {
        return tcpInterfaceId;
    }

    public void setTcpInterfaceId(Long tcpInterfaceId) {
        this.tcpInterfaceId = tcpInterfaceId;
    }

    public String getTcpInterfaceRequestUri() {
        return tcpInterfaceRequestUri;
    }

    public void setTcpInterfaceRequestUri(String tcpInterfaceRequestUri) {
        this.tcpInterfaceRequestUri = tcpInterfaceRequestUri == null ? null : tcpInterfaceRequestUri.trim();
    }

    public Long getTcpInterfaceSpaceId() {
        return tcpInterfaceSpaceId;
    }

    public void setTcpInterfaceSpaceId(Long tcpInterfaceSpaceId) {
        this.tcpInterfaceSpaceId = tcpInterfaceSpaceId;
    }

    public String getTcpInterface() {
        return tcpInterface;
    }

    public void setTcpInterface(String tcpInterface) {
        this.tcpInterface = tcpInterface == null ? null : tcpInterface.trim();
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