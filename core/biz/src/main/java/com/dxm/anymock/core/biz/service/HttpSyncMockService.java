package com.dxm.anymock.core.biz.service;

import com.dxm.anymock.core.biz.HttpMockContext;

import java.io.IOException;

public interface HttpSyncMockService {
    void mock(HttpMockContext httpMockContext) throws IOException;
}
