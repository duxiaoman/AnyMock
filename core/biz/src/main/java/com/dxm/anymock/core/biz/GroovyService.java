package com.dxm.anymock.core.biz;

import com.dxm.anymock.core.biz.entity.MockContext;

public interface GroovyService {
    String exec(MockContext mockContext, String text);
    void initBinding(MockContext mockContext);
    void bindSyncProperty(MockContext mockContext);
    void bindAsyncProperty(MockContext mockContext);
}
