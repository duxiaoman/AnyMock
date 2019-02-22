package com.dxm.anymock.common.dal.entity;

public class HttpInterfaceSnapshotPOWithBLOBs extends HttpInterfaceSnapshotPO {
    private String responseBody;

    private String callbackRequestBody;

    private String branchJumpScript;

    private String syncScript;

    private String asyncScript;

    private String responseHeaderList;

    private String callbackRequestHeaderList;

    private String branchScriptList;

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody == null ? null : responseBody.trim();
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

    public String getResponseHeaderList() {
        return responseHeaderList;
    }

    public void setResponseHeaderList(String responseHeaderList) {
        this.responseHeaderList = responseHeaderList == null ? null : responseHeaderList.trim();
    }

    public String getCallbackRequestHeaderList() {
        return callbackRequestHeaderList;
    }

    public void setCallbackRequestHeaderList(String callbackRequestHeaderList) {
        this.callbackRequestHeaderList = callbackRequestHeaderList == null ? null : callbackRequestHeaderList.trim();
    }

    public String getBranchScriptList() {
        return branchScriptList;
    }

    public void setBranchScriptList(String branchScriptList) {
        this.branchScriptList = branchScriptList == null ? null : branchScriptList.trim();
    }
}