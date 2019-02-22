package com.dxm.anymock.common.base.entity;

import com.dxm.anymock.common.base.enums.HttpInterfaceOpType;

import java.util.Date;

public class HttpInterfaceSnapshot {
    private HttpInterface snapshot;

    private Integer opType;
    private String opUser;
    private Date snapshotTime;

    public HttpInterface getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(HttpInterface snapshot) {
        this.snapshot = snapshot;
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
}
