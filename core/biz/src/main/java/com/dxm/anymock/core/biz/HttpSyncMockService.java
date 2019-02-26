package com.dxm.anymock.core.biz;

import com.dxm.anymock.core.biz.entity.MockContext;

import java.io.IOException;

public interface HttpSyncMockService {
    void mock(MockContext mockContext) throws IOException;
}
