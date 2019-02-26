package com.dxm.anymock.common.base.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class HttpInterfaceSnapshot {
    private Long id;
    private Integer opType;
    private String opUser;
    private Date snapshotTime;
    private HttpInterface httpInterface;

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

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
