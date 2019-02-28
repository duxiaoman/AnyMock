package com.dxm.anymock.core.biz;

import com.dxm.anymock.core.biz.entity.HttpMockContext;

public interface GroovyService {
    String exec(HttpMockContext httpMockContext, String text);
    void initBinding(HttpMockContext httpMockContext);
    void bindSyncProperty(HttpMockContext httpMockContext);
    void bindAsyncProperty(HttpMockContext httpMockContext);
}
