package com.dxm.anymock.core.biz;

import com.dxm.anymock.core.biz.entity.MockContext;

public interface GroovyService {
    String exec(MockContext mockContext, String text);
}
