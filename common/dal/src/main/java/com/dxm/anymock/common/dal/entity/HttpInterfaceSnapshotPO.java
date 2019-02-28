package com.dxm.anymock.common.dal.entity;

import java.util.Date;

public class HttpInterfaceSnapshotPO {
    private Long id;

    private Long httpInterfaceId;

    private String httpInterfaceRequestUri;

    private String httpInterfaceRequestMethod;

    private String httpInterfaceDescription;

    private Boolean httpInterfaceNeedAsyncCallback;

    private Integer httpInterfaceConfigMode;

    private String httpInterfaceCallbackRequestUrl;

    private String httpInterfaceCallbackRequestMethod;

    private Integer httpInterfaceSyncDelay;

    private Integer httpInterfaceAsyncDelay;

    private Boolean httpInterfaceStart;

    private Long httpInterfaceSpaceId;

    private String httpInterfaceLastUpdateUser;

    private Date httpInterfaceLastUpdateTime;

    private Integer opType;

    private String opUser;

    private Date snapshotTime;

    private String httpInterfaceName;

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

    public String getHttpInterfaceDescription() {
        return httpInterfaceDescription;
    }

    public void setHttpInterfaceDescription(String httpInterfaceDescription) {
        this.httpInterfaceDescription = httpInterfaceDescription == null ? null : httpInterfaceDescription.trim();
    }

    public Boolean getHttpInterfaceNeedAsyncCallback() {
        return httpInterfaceNeedAsyncCallback;
    }

    public void setHttpInterfaceNeedAsyncCallback(Boolean httpInterfaceNeedAsyncCallback) {
        this.httpInterfaceNeedAsyncCallback = httpInterfaceNeedAsyncCallback;
    }

    public Integer getHttpInterfaceConfigMode() {
        return httpInterfaceConfigMode;
    }

    public void setHttpInterfaceConfigMode(Integer httpInterfaceConfigMode) {
        this.httpInterfaceConfigMode = httpInterfaceConfigMode;
    }

    public String getHttpInterfaceCallbackRequestUrl() {
        return httpInterfaceCallbackRequestUrl;
    }

    public void setHttpInterfaceCallbackRequestUrl(String httpInterfaceCallbackRequestUrl) {
        this.httpInterfaceCallbackRequestUrl = httpInterfaceCallbackRequestUrl == null ? null : httpInterfaceCallbackRequestUrl.trim();
    }

    public String getHttpInterfaceCallbackRequestMethod() {
        return httpInterfaceCallbackRequestMethod;
    }

    public void setHttpInterfaceCallbackRequestMethod(String httpInterfaceCallbackRequestMethod) {
        this.httpInterfaceCallbackRequestMethod = httpInterfaceCallbackRequestMethod == null ? null : httpInterfaceCallbackRequestMethod.trim();
    }

    public Integer getHttpInterfaceSyncDelay() {
        return httpInterfaceSyncDelay;
    }

    public void setHttpInterfaceSyncDelay(Integer httpInterfaceSyncDelay) {
        this.httpInterfaceSyncDelay = httpInterfaceSyncDelay;
    }

    public Integer getHttpInterfaceAsyncDelay() {
        return httpInterfaceAsyncDelay;
    }

    public void setHttpInterfaceAsyncDelay(Integer httpInterfaceAsyncDelay) {
        this.httpInterfaceAsyncDelay = httpInterfaceAsyncDelay;
    }

    public Boolean getHttpInterfaceStart() {
        return httpInterfaceStart;
    }

    public void setHttpInterfaceStart(Boolean httpInterfaceStart) {
        this.httpInterfaceStart = httpInterfaceStart;
    }

    public Long getHttpInterfaceSpaceId() {
        return httpInterfaceSpaceId;
    }

    public void setHttpInterfaceSpaceId(Long httpInterfaceSpaceId) {
        this.httpInterfaceSpaceId = httpInterfaceSpaceId;
    }

    public String getHttpInterfaceLastUpdateUser() {
        return httpInterfaceLastUpdateUser;
    }

    public void setHttpInterfaceLastUpdateUser(String httpInterfaceLastUpdateUser) {
        this.httpInterfaceLastUpdateUser = httpInterfaceLastUpdateUser == null ? null : httpInterfaceLastUpdateUser.trim();
    }

    public Date getHttpInterfaceLastUpdateTime() {
        return httpInterfaceLastUpdateTime;
    }

    public void setHttpInterfaceLastUpdateTime(Date httpInterfaceLastUpdateTime) {
        this.httpInterfaceLastUpdateTime = httpInterfaceLastUpdateTime;
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
        this.opUser = opUser == null ? null : opUser.trim();
    }

    public Date getSnapshotTime() {
        return snapshotTime;
    }

    public void setSnapshotTime(Date snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    public String getHttpInterfaceName() {
        return httpInterfaceName;
    }

    public void setHttpInterfaceName(String httpInterfaceName) {
        this.httpInterfaceName = httpInterfaceName == null ? null : httpInterfaceName.trim();
    }
}