package com.dxm.anymock.common.dal.dto;

import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.HttpInterfaceSnapshot;

import java.util.Date;

public class HttpInterfaceSnapshotDTO {
    private Long id;
    private Integer opType;
    private String opUser;
    private Date snapshotTime;
    private HttpInterface httpInterface;

    public HttpInterfaceSnapshotDTO(HttpInterfaceSnapshot httpInterfaceSnapshot) {
        this.id = httpInterfaceSnapshot.getId();
        this.opType = httpInterfaceSnapshot.getOpType();
        this.opUser = httpInterfaceSnapshot.getOpUser();
        this.snapshotTime = httpInterfaceSnapshot.getSnapshotTime();

        this.httpInterface = new HttpInterface();
        this.httpInterface.setId(httpInterfaceSnapshot.getHttpInterfaceId());
        this.httpInterface.setRequestUri(httpInterfaceSnapshot.getHttpInterfaceRequestUri());
        this.httpInterface.setRequestMethod(httpInterfaceSnapshot.getHttpInterfaceRequestMethod());
        this.httpInterface.setDescription(httpInterfaceSnapshot.getHttpInterfaceDescription());
        this.httpInterface.setNeedAsyncCallback(httpInterfaceSnapshot.getHttpInterfaceNeedAsyncCallback());
        this.httpInterface.setConfigMode(httpInterfaceSnapshot.getHttpInterfaceConfigMode());
        this.httpInterface.setCallbackRequestUrl(httpInterfaceSnapshot.getHttpInterfaceCallbackRequestUrl());
        this.httpInterface.setCallbackRequestMethod(httpInterfaceSnapshot.getHttpInterfaceCallbackRequestMethod());
        this.httpInterface.setSyncDelay(httpInterfaceSnapshot.getHttpInterfaceSyncDelay());
        this.httpInterface.setAsyncDelay(httpInterfaceSnapshot.getHttpInterfaceAsyncDelay());
        this.httpInterface.setStart(httpInterfaceSnapshot.getHttpInterfaceStart());
        this.httpInterface.setSubSpaceId(httpInterfaceSnapshot.getHttpInterfaceSubSpaceId());
        this.httpInterface.setLastUpdateUser(httpInterfaceSnapshot.getHttpInterfaceLastUpdateUser());
        this.httpInterface.setLastUpdateTime(httpInterfaceSnapshot.getHttpInterfaceLastUpdateTime());
        this.httpInterface.setResponseBody(httpInterfaceSnapshot.getHttpInterfaceResponseBody());
        this.httpInterface.setCallbackRequestBody(httpInterfaceSnapshot.getHttpInterfaceCallbackRequestBody());
        this.httpInterface.setBranchJumpScript(httpInterfaceSnapshot.getHttpInterfaceBranchJumpScript());
        this.httpInterface.setSyncScript(httpInterfaceSnapshot.getHttpInterfaceSyncScript());
        this.httpInterface.setAsyncScript(httpInterfaceSnapshot.getHttpInterfaceAsyncScript());
        this.httpInterface.setResponseHeaderList(httpInterfaceSnapshot.getHttpInterfaceResponseHeaderList());
        this.httpInterface.setCallbackRequestHeaderList(httpInterfaceSnapshot.getHttpInterfaceCallbackRequestHeaderList());
        this.httpInterface.setBranchScriptList(httpInterfaceSnapshot.getHttpInterfaceBranchScriptList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }

    public Date getSnapshotTime() {
        return snapshotTime;
    }

    public void setSnapshotTime(Date snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    public HttpInterface getHttpInterface() {
        return httpInterface;
    }

    public void setHttpInterface(HttpInterface httpInterface) {
        this.httpInterface = httpInterface;
    }
}
