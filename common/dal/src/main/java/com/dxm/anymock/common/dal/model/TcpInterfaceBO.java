/*
*  Copyright 2018-2019 Duxiaoman Group Holding Ltd.

*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at

*  http://www.apache.org/licenses/LICENSE-2.0

*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.

*/


package com.dxm.anymock.common.dal.model;

import com.dxm.anymock.common.dal.model.enums.AccessAuthority;
import com.dxm.anymock.common.dal.model.enums.ConfigMode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by jff on 2019/8/26.
 */

public class TcpInterfaceBO extends TcpInterfaceKeyBO implements Serializable {

    @NotNull(groups = {TcpInterfaceKeyBO.Update.class})
    private Long id;

    @NotNull(groups = {TcpInterfaceKeyBO.Insert.class, TcpInterfaceKeyBO.Update.class})
    private String description;

    private Boolean needAsyncCallback;

    @NotNull(groups = {TcpInterfaceKeyBO.Insert.class, TcpInterfaceKeyBO.Update.class})
    private ConfigMode configMode;

    private String responseBody;

    private String callbackRequestUrl;

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

    private AccessAuthority accessAuthority;

    private Date gmtCreate;

    private Date gmtModified;


    public List<InterfaceBranchBO> getBranchScriptList() {
        return branchScriptList;
    }

    public void setBranchScriptList(List<InterfaceBranchBO> branchScriptList) {
        this.branchScriptList = branchScriptList;
    }

    @NotNull(groups = {Insert.class, Update.class})
    private List<InterfaceBranchBO> branchScriptList;

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
        this.description = description == null ? null : description.trim();
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
        this.responseBody = responseBody == null ? null : responseBody.trim();
    }

    public String getCallbackRequestUrl() {
        return callbackRequestUrl;
    }

    public void setCallbackRequestUrl(String callbackRequestUrl) {
        this.callbackRequestUrl = callbackRequestUrl == null ? null : callbackRequestUrl.trim();
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
}
