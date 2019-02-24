package com.dxm.anymock.common.base.entity;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.check.HttpInterfaceIdCheck;
import com.dxm.anymock.common.base.check.CommonIdCheck;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class HttpInterfaceSnapshot {

    @NotNull(groups = CommonIdCheck.class)
    private Long id;

    @NotNull(groups = HttpInterfaceIdCheck.class)
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

    private String httpInterfaceResponseBody;

    private String httpInterfaceCallbackRequestBody;

    private String httpInterfaceBranchJumpScript;

    private String httpInterfaceSyncScript;

    private String httpInterfaceAsyncScript;

    private List<HttpHeader> httpInterfaceResponseHeaderList;

    private List<HttpHeader> httpInterfaceCallbackRequestHeaderList;

    private List<BranchScript> httpInterfaceBranchScriptList;

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
        this.httpInterfaceRequestUri = httpInterfaceRequestUri;
    }

    public String getHttpInterfaceRequestMethod() {
        return httpInterfaceRequestMethod;
    }

    public void setHttpInterfaceRequestMethod(String httpInterfaceRequestMethod) {
        this.httpInterfaceRequestMethod = httpInterfaceRequestMethod;
    }

    public String getHttpInterfaceDescription() {
        return httpInterfaceDescription;
    }

    public void setHttpInterfaceDescription(String httpInterfaceDescription) {
        this.httpInterfaceDescription = httpInterfaceDescription;
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
        this.httpInterfaceCallbackRequestUrl = httpInterfaceCallbackRequestUrl;
    }

    public String getHttpInterfaceCallbackRequestMethod() {
        return httpInterfaceCallbackRequestMethod;
    }

    public void setHttpInterfaceCallbackRequestMethod(String httpInterfaceCallbackRequestMethod) {
        this.httpInterfaceCallbackRequestMethod = httpInterfaceCallbackRequestMethod;
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
        this.httpInterfaceLastUpdateUser = httpInterfaceLastUpdateUser;
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
        this.opUser = opUser;
    }

    public Date getSnapshotTime() {
        return snapshotTime;
    }

    public void setSnapshotTime(Date snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    public String getHttpInterfaceResponseBody() {
        return httpInterfaceResponseBody;
    }

    public void setHttpInterfaceResponseBody(String httpInterfaceResponseBody) {
        this.httpInterfaceResponseBody = httpInterfaceResponseBody;
    }

    public String getHttpInterfaceCallbackRequestBody() {
        return httpInterfaceCallbackRequestBody;
    }

    public void setHttpInterfaceCallbackRequestBody(String httpInterfaceCallbackRequestBody) {
        this.httpInterfaceCallbackRequestBody = httpInterfaceCallbackRequestBody;
    }

    public String getHttpInterfaceBranchJumpScript() {
        return httpInterfaceBranchJumpScript;
    }

    public void setHttpInterfaceBranchJumpScript(String httpInterfaceBranchJumpScript) {
        this.httpInterfaceBranchJumpScript = httpInterfaceBranchJumpScript;
    }

    public String getHttpInterfaceSyncScript() {
        return httpInterfaceSyncScript;
    }

    public void setHttpInterfaceSyncScript(String httpInterfaceSyncScript) {
        this.httpInterfaceSyncScript = httpInterfaceSyncScript;
    }

    public String getHttpInterfaceAsyncScript() {
        return httpInterfaceAsyncScript;
    }

    public void setHttpInterfaceAsyncScript(String httpInterfaceAsyncScript) {
        this.httpInterfaceAsyncScript = httpInterfaceAsyncScript;
    }

    public List<HttpHeader> getHttpInterfaceResponseHeaderList() {
        return httpInterfaceResponseHeaderList;
    }

    public void setHttpInterfaceResponseHeaderList(List<HttpHeader> httpInterfaceResponseHeaderList) {
        this.httpInterfaceResponseHeaderList = httpInterfaceResponseHeaderList;
    }

    public List<HttpHeader> getHttpInterfaceCallbackRequestHeaderList() {
        return httpInterfaceCallbackRequestHeaderList;
    }

    public void setHttpInterfaceCallbackRequestHeaderList(List<HttpHeader> httpInterfaceCallbackRequestHeaderList) {
        this.httpInterfaceCallbackRequestHeaderList = httpInterfaceCallbackRequestHeaderList;
    }

    public List<BranchScript> getHttpInterfaceBranchScriptList() {
        return httpInterfaceBranchScriptList;
    }

    public void setHttpInterfaceBranchScriptList(List<BranchScript> httpInterfaceBranchScriptList) {
        this.httpInterfaceBranchScriptList = httpInterfaceBranchScriptList;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
