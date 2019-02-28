package com.dxm.anymock.core.biz;

import com.dxm.anymock.core.biz.entity.HttpMockContext;

public interface HttpAsyncMockService {
    void mock(HttpMockContext httpMockContext) throws Exception;
}
