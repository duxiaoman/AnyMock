package com.dxm.anymock.core.biz.service;

import com.dxm.anymock.core.biz.HttpMockContext;

import javax.servlet.http.HttpServletRequest;

public interface HttpAsyncMockService {
    void mock(HttpMockContext httpMockContext, HttpServletRequest request) throws Exception;
}
