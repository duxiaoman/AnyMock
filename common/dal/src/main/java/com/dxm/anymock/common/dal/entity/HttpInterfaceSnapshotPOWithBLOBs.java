package com.dxm.anymock.common.dal.entity;

public class HttpInterfaceSnapshotPOWithBLOBs extends HttpInterfaceSnapshotPO {
    private String httpInterfaceResponseBody;

    private String httpInterfaceCallbackRequestBody;

    private String httpInterfaceBranchJumpScript;

    private String httpInterfaceSyncScript;

    private String httpInterfaceAsyncScript;

    private String httpInterfaceResponseHeaderList;

    private String httpInterfaceCallbackRequestHeaderList;

    private String httpInterfaceBranchScriptList;

    public String getHttpInterfaceResponseBody() {
        return httpInterfaceResponseBody;
    }

    public void setHttpInterfaceResponseBody(String httpInterfaceResponseBody) {
        this.httpInterfaceResponseBody = httpInterfaceResponseBody == null ? null : httpInterfaceResponseBody.trim();
    }

    public String getHttpInterfaceCallbackRequestBody() {
        return httpInterfaceCallbackRequestBody;
    }

    public void setHttpInterfaceCallbackRequestBody(String httpInterfaceCallbackRequestBody) {
        this.httpInterfaceCallbackRequestBody = httpInterfaceCallbackRequestBody == null ? null : httpInterfaceCallbackRequestBody.trim();
    }

    public String getHttpInterfaceBranchJumpScript() {
        return httpInterfaceBranchJumpScript;
    }

    public void setHttpInterfaceBranchJumpScript(String httpInterfaceBranchJumpScript) {
        this.httpInterfaceBranchJumpScript = httpInterfaceBranchJumpScript == null ? null : httpInterfaceBranchJumpScript.trim();
    }

    public String getHttpInterfaceSyncScript() {
        return httpInterfaceSyncScript;
    }

    public void setHttpInterfaceSyncScript(String httpInterfaceSyncScript) {
        this.httpInterfaceSyncScript = httpInterfaceSyncScript == null ? null : httpInterfaceSyncScript.trim();
    }

    public String getHttpInterfaceAsyncScript() {
        return httpInterfaceAsyncScript;
    }

    public void setHttpInterfaceAsyncScript(String httpInterfaceAsyncScript) {
        this.httpInterfaceAsyncScript = httpInterfaceAsyncScript == null ? null : httpInterfaceAsyncScript.trim();
    }

    public String getHttpInterfaceResponseHeaderList() {
        return httpInterfaceResponseHeaderList;
    }

    public void setHttpInterfaceResponseHeaderList(String httpInterfaceResponseHeaderList) {
        this.httpInterfaceResponseHeaderList = httpInterfaceResponseHeaderList == null ? null : httpInterfaceResponseHeaderList.trim();
    }

    public String getHttpInterfaceCallbackRequestHeaderList() {
        return httpInterfaceCallbackRequestHeaderList;
    }

    public void setHttpInterfaceCallbackRequestHeaderList(String httpInterfaceCallbackRequestHeaderList) {
        this.httpInterfaceCallbackRequestHeaderList = httpInterfaceCallbackRequestHeaderList == null ? null : httpInterfaceCallbackRequestHeaderList.trim();
    }

    public String getHttpInterfaceBranchScriptList() {
        return httpInterfaceBranchScriptList;
    }

    public void setHttpInterfaceBranchScriptList(String httpInterfaceBranchScriptList) {
        this.httpInterfaceBranchScriptList = httpInterfaceBranchScriptList == null ? null : httpInterfaceBranchScriptList.trim();
    }
}