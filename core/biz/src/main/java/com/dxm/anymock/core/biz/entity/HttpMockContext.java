package com.dxm.anymock.core.biz.entity;

import com.dxm.anymock.common.base.entity.RequestType;
import groovy.lang.Binding;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.util.Enumeration;

public class HttpMockContext {
    private RequestType requestType;
    private HttpInterface httpInterface;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;
    private ConfigMode configMode;
    private HttpURLConnection httpURLConnection;
    private Binding groovyBinding;
    private BranchScript branchScript;
    private String rawHttpRequestMsg;

    public String getRawHttpRequestMsg() {
        HttpServletRequest request = httpServletRequest;
        if (rawHttpRequestMsg == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(request.getMethod());
            stringBuilder.append(" ");
            stringBuilder.append(request.getRequestURI());
            if (request.getQueryString() != null) {
                stringBuilder.append("?");
                stringBuilder.append(request.getQueryString());
            }
            stringBuilder.append("\n");

            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String)headerNames.nextElement();
                stringBuilder.append(key);
                stringBuilder.append(":");
                stringBuilder.append(request.getHeader(key));
                stringBuilder.append("\n");
            }
            stringBuilder.append("\n");
            stringBuilder.append(request.getAttribute("body"));
            rawHttpRequestMsg = stringBuilder.toString();
        }
        return rawHttpRequestMsg;
    }

    public HttpMockContext(HttpServletRequest request, HttpServletResponse response) {
        this.httpServletRequest = request;
        this.httpServletResponse = response;
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
