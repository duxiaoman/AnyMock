package com.dxm.anymock.common.dal.entity;

import java.util.Date;

public class HttpInterfaceDO {
    private Long id;

    private String requestUri;

    private String requestMethod;

    private String description;

    private Boolean needAsyncCallback;

    private String configMode;

    private String responseBody;

    private String callbackRequestUrl;

    private String callbackRequestMethod;

    private String callbackRequestBody;

    private String branchJumpScript;

    private String syncScript;

    private String asyncScript;

    private Integer syncDelay;

    private Integer asyncDelay;

    private Boolean start;

    private Long spaceId;

    private String lastUpdateUser;

    private String name;

    private String accessAuthority;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri == null ? null : requestUri.trim();
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod == null ? null : requestMethod.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getNeedAsyncCallback() {
        return needAsyncCallback;
    }

    public void setNeedAsyncCallback(Boolean needAsyncCallback) {
        this.needAsyncCallback = needAsyncCallback;
    }

    public String getConfigMode() {
        return configMode;
    }

    public void setConfigMode(String configMode) {
        this.configMode = configMode == null ? null : configMode.trim();
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody == null ? null : responseBody.trim();
    }

    public String getCallbackRequestUrl() {
        return callbackRequestUrl;
    }

    public void setCallbackRequestUrl(String callbackRequestUrl) {
        this.callbackRequestUrl = callbackRequestUrl == null ? null : callbackRequestUrl.trim();
    }

    public String getCallbackRequestMethod() {
        return callbackRequestMethod;
    }

    public void setCallbackRequestMethod(String callbackRequestMethod) {
        this.callbackRequestMethod = callbackRequestMethod == null ? null : callbackRequestMethod.trim();
    }

    public String getCallbackRequestBody() {
        return callbackRequestBody;
    }

    public void setCallbackRequestBody(String callbackRequestBody) {
        this.callbackRequestBody = callbackRequestBody == null ? null : callbackRequestBody.trim();
    }

    public String getBranchJumpScript() {
        return branchJumpScript;
    }

    public void setBranchJumpScript(String branchJumpScript) {
        this.branchJumpScript = branchJumpScript == null ? null : branchJumpScript.trim();
    }

    public String getSyncScript() {
        return syncScript;
    }

    public void setSyncScript(String syncScript) {
        this.syncScript = syncScript == null ? null : syncScript.trim();
    }

    public String getAsyncScript() {
        return asyncScript;
    }

    public void setAsyncScript(String asyncScript) {
        this.asyncScript = asyncScript == null ? null : asyncScript.trim();
    }

    public Integer getSyncDelay() {
        return syncDelay;
    }

    public void setSyncDelay(Integer syncDelay) {
        this.syncDelay = syncDelay;
    }

    public Integer getAsyncDelay() {
        return asyncDelay;
    }

    public void setAsyncDelay(Integer asyncDelay) {
        this.asyncDelay = asyncDelay;
    }

    public Boolean getStart() {
        return start;
    }

    public void setStart(Boolean start) {
        this.start = start;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAccessAuthority() {
        return accessAuthority;
    }

    public void setAccessAuthority(String accessAuthority) {
        this.accessAuthority = accessAuthority == null ? null : accessAuthority.trim();
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