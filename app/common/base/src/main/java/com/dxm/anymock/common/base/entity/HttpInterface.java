package com.dxm.anymock.common.base.entity;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.check.CommonDeleteCheck;
import com.dxm.anymock.common.base.check.CommonInsertCheck;
import com.dxm.anymock.common.base.check.CommonUpdateCheck;
import com.dxm.anymock.common.base.check.SpaceIdCheck;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class HttpInterface implements Serializable {

    @NotNull(groups = {CommonUpdateCheck.class, CommonDeleteCheck.class})
    private Long id;

    @NotBlank(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String requestUri;

    @NotBlank(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String requestMethod;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String description;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private Boolean needAsyncCallback;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private Integer configMode;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String responseBody;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String callbackRequestUrl;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String callbackRequestMethod;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String callbackRequestBody;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String branchJumpScript;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String syncScript;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String asyncScript;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    @Min(value = 0, groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    @Max(value = GlobalConstant.MAX_SYNC_DELAY, groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private Integer syncDelay;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    @Min(value = 0, groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    @Max(value = GlobalConstant.MAX_ASYNC_DELAY, groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private Integer asyncDelay;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private Boolean start;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class, SpaceIdCheck.class})
    private Long spaceId;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    @Valid
    private List<HttpHeader> responseHeaderList;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    @Valid
    private List<HttpHeader> callbackRequestHeaderList;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    @Valid
    private List<BranchScript> branchScriptList;

    private String lastUpdateUser;

    private Date lastUpdateTime;

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
        this.requestUri = requestUri;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getNeedAsyncCallback() {
        return needAsyncCallback;
    }

    public void setNeedAsyncCallback(Boolean needAsyncCallback) {
        this.needAsyncCallback = needAsyncCallback;
    }

    public Integer getConfigMode() {
        return configMode;
    }

    public void setConfigMode(Integer configMode) {
        this.configMode = configMode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getCallbackRequestUrl() {
        return callbackRequestUrl;
    }

    public void setCallbackRequestUrl(String callbackRequestUrl) {
        this.callbackRequestUrl = callbackRequestUrl;
    }

    public String getCallbackRequestMethod() {
        return callbackRequestMethod;
    }

    public void setCallbackRequestMethod(String callbackRequestMethod) {
        this.callbackRequestMethod = callbackRequestMethod;
    }

    public String getCallbackRequestBody() {
        return callbackRequestBody;
    }

    public void setCallbackRequestBody(String callbackRequestBody) {
        this.callbackRequestBody = callbackRequestBody;
    }

    public String getBranchJumpScript() {
        return branchJumpScript;
    }

    public void setBranchJumpScript(String branchJumpScript) {
        this.branchJumpScript = branchJumpScript;
    }

    public String getSyncScript() {
        return syncScript;
    }

    public void setSyncScript(String syncScript) {
        this.syncScript = syncScript;
    }

    public String getAsyncScript() {
        return asyncScript;
    }

    public void setAsyncScript(String asyncScript) {
        this.asyncScript = asyncScript;
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

    public List<HttpHeader> getResponseHeaderList() {
        return responseHeaderList;
    }

    public void setResponseHeaderList(List<HttpHeader> responseHeaderList) {
        this.responseHeaderList = responseHeaderList;
    }

    public List<HttpHeader> getCallbackRequestHeaderList() {
        return callbackRequestHeaderList;
    }

    public void setCallbackRequestHeaderList(List<HttpHeader> callbackRequestHeaderList) {
        this.callbackRequestHeaderList = callbackRequestHeaderList;
    }

    public List<BranchScript> getBranchScriptList() {
        return branchScriptList;
    }

    public void setBranchScriptList(List<BranchScript> branchScriptList) {
        this.branchScriptList = branchScriptList;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
