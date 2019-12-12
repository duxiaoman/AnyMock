package com.dxm.anymock.core.biz.service;

import com.dxm.anymock.core.biz.HttpMockContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpSyncMockService {
    void mock(HttpMockContext context, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
