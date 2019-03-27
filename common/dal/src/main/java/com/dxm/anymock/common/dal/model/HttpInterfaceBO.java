package com.dxm.anymock.common.dal.model;

import com.dxm.anymock.common.dal.model.enums.AccessAuthority;
import com.dxm.anymock.common.dal.model.enums.ConfigMode;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static com.dxm.anymock.common.dal.DalConstants.MAX_ASYNC_DELAY;
import static com.dxm.anymock.common.dal.DalConstants.MAX_SYNC_DELAY;

public class HttpInterfaceBO extends HttpInterfaceKeyBO implements Serializable {

    @NotNull(groups = {Update.class})
    private Long id;

    @NotNull(groups = {Insert.class, Update.class})
    private String description;

    @NotNull(groups = {Insert.class, Update.class})
    private Boolean needAsyncCallback;

    @NotNull(groups = {Insert.class, Update.class})
    private ConfigMode configMode;

    @NotNull(groups = {Insert.class, Update.class})
    private String responseBody;

    @NotNull(groups = {Insert.class, Update.class})
    private String callbackRequestUrl;

    @NotNull(groups = {Insert.class, Update.class})
    private String callbackRequestMethod;

    @NotNull(groups = {Insert.class, Update.class})
    private String callbackRequestBody;

    @NotNull(groups = {Insert.class, Update.class})
    private String branchJumpScript;

    @NotNull(groups = {Insert.class, Update.class})
    private String syncScript;

    @NotNull(groups = {Insert.class, Update.class})
    private String asyncScript;

    @NotNull(groups = {Insert.class, Update.class})
    @Min(value = 0, groups = {Insert.class, Update.class})
    @Max(value = MAX_SYNC_DELAY, groups = {Insert.class, Update.class})
    private Integer syncDelay;

    @NotNull(groups = {Insert.class, Update.class})
    @Min(value = 0, groups = {Insert.class, Update.class})
    @Max(value = MAX_ASYNC_DELAY, groups = {Insert.class, Update.class})
    private Integer asyncDelay;

    @NotNull(groups = {Insert.class, Update.class})
    private Boolean start;

    @NotNull(groups = {Insert.class, Update.class})
    private Long spaceId;

    private String lastUpdateUser;

    @NotBlank(groups = {Insert.class, Update.class})
    private String name;

    private AccessAuthority accessAuthority;

    private Date gmtCreate;

    private Date gmtModified;

    @NotNull(groups = {Insert.class, Update.class})
    private List<HttpInterfaceHeaderBO> responseHeaderList;

    @NotNull(groups = {Insert.class, Update.class})
    private List<HttpInterfaceHeaderBO> callbackRequestHeaderList;

    @NotNull(groups = {Insert.class, Update.class})
    private List<HttpInterfaceBranchBO> branchScriptList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ConfigMode getConfigMode() {
        return configMode;
    }

    public void setConfigMode(ConfigMode configMode) {
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

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccessAuthority getAccessAuthority() {
        return accessAuthority;
    }

    public void setAccessAuthority(AccessAuthority accessAuthority) {
        this.accessAuthority = accessAuthority;
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

    public List<HttpInterfaceHeaderBO> getResponseHeaderList() {
        return responseHeaderList;
    }

    public void setResponseHeaderList(List<HttpInterfaceHeaderBO> responseHeaderList) {
        this.responseHeaderList = responseHeaderList;
    }

    public List<HttpInterfaceHeaderBO> getCallbackRequestHeaderList() {
        return callbackRequestHeaderList;
    }

    public void setCallbackRequestHeaderList(List<HttpInterfaceHeaderBO> callbackRequestHeaderList) {
        this.callbackRequestHeaderList = callbackRequestHeaderList;
    }

    public List<HttpInterfaceBranchBO> getBranchScriptList() {
        return branchScriptList;
    }

    public void setBranchScriptList(List<HttpInterfaceBranchBO> branchScriptList) {
        this.branchScriptList = branchScriptList;
    }
}
