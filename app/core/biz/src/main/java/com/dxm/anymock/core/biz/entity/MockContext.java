package com.dxm.anymock.core.biz.entity;

import com.dxm.anymock.common.base.entity.BranchScript;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.RequestType;
import com.dxm.anymock.common.base.enums.ConfigMode;
import groovy.lang.Binding;
import org.slf4j.profiler.Profiler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.util.LinkedList;

public class MockContext {
    private RequestType requestType;
    private HttpInterface httpInterface;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private ConfigMode configMode;
    private HttpURLConnection httpURLConnection;
    private Binding groovyBinding;
    private BranchScript branchScript;

    private Profiler profiler;

    public MockContext() {
        this.profiler = new Profiler("mock");
    }

    public Profiler getProfiler() {
        return profiler;
    }

    public void setProfiler(Profiler profiler) {
        this.profiler = profiler;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public HttpInterface getHttpInterface() {
        return httpInterface;
    }

    public void setHttpInterface(HttpInterface httpInterface) {
        this.httpInterface = httpInterface;
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

    public ConfigMode getConfigMode() {
        return configMode;
    }

    public void setConfigMode(ConfigMode configMode) {
        this.configMode = configMode;
    }

    public HttpURLConnection getHttpURLConnection() {
        return httpURLConnection;
    }

    public void setHttpURLConnection(HttpURLConnection httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }

    public Binding getGroovyBinding() {
        return groovyBinding;
    }

    public void setGroovyBinding(Binding groovyBinding) {
        this.groovyBinding = groovyBinding;
    }

    public BranchScript getBranchScript() {
        return branchScript;
    }

    public void setBranchScript(BranchScript branchScript) {
        this.branchScript = branchScript;
    }
}
