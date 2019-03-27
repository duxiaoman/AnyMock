package com.dxm.anymock.core.biz.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpMockService {
    void mock(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
