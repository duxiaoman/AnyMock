package com.dxm.anymock.core.biz;

import com.dxm.anymock.core.biz.entity.HttpMockContext;

import java.io.IOException;

public interface HttpSyncMockService {
    void mock(HttpMockContext httpMockContext) throws IOException;
}
