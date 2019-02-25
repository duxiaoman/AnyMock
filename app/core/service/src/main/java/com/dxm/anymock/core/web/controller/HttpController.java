package com.dxm.anymock.core.web.controller;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.core.biz.HttpMockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.ProfilerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HttpController {

    private static final Logger logger = LoggerFactory.getLogger(GlobalConstant.MOCK_SERVICE_LOGGER);

    @Autowired
    private HttpMockService httpMockService;

    @RequestMapping(value = "/**/*")
    public void handleHttpRequests(HttpServletRequest request, HttpServletResponse response) throws IOException {
        httpMockService.mock(request, response);
    }
}
