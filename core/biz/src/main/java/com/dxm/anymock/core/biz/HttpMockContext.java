package com.dxm.anymock.core.biz;

import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.common.dal.model.HttpInterfaceBranchBO;
import groovy.lang.Binding;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;

public class HttpMockContext {
    private HttpInterfaceBO httpInterfaceBO;
    private HttpInterfaceBranchBO httpInterfaceBranchBO;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private Binding groovyBinding;
    private String rawHttpRequestMsg;

    public HttpInterfaceBO getHttpInterfaceBO() {
        return httpInterfaceBO;
    }

    public void setHttpInterfaceBO(HttpInterfaceBO httpInterfaceBO) {
        this.httpInterfaceBO = httpInterfaceBO;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public Binding getGroovyBinding() {
        return groovyBinding;
    }

    public void setGroovyBinding(Binding groovyBinding) {
        this.groovyBinding = groovyBinding;
    }

    public String getRawHttpRequestMsg() {
        return rawHttpRequestMsg;
    }

    public void setRawHttpRequestMsg(String rawHttpRequestMsg) {
        this.rawHttpRequestMsg = rawHttpRequestMsg;
    }

    public HttpInterfaceBranchBO getHttpInterfaceBranchBO() {
        return httpInterfaceBranchBO;
    }

    public void setHttpInterfaceBranchBO(HttpInterfaceBranchBO httpInterfaceBranchBO) {
        this.httpInterfaceBranchBO = httpInterfaceBranchBO;
    }
}
