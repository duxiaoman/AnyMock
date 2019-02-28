package com.dxm.anymock.core.biz.impl;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.entity.BranchScript;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.entity.RequestType;
import com.dxm.anymock.common.base.enums.ConfigMode;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.common.dal.dao.HttpInterfaceDao;
import com.dxm.anymock.common.dal.dao.RedisDao;
import com.dxm.anymock.core.biz.GroovyService;
import com.dxm.anymock.core.biz.HttpSyncMockService;
import com.dxm.anymock.core.biz.entity.MockContext;
import groovy.lang.Binding;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.ProfilerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HttpSyncMockServiceImpl implements HttpSyncMockService {

    private static final Logger logger = LoggerFactory.getLogger(GlobalConstant.MOCK_SERVICE_LOGGER);

    @Autowired
    private GroovyService groovyService;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private HttpInterfaceDao httpInterfaceDao;

    private void mockSyncDelay(MockContext mockContext) {
        HttpInterface httpInterface = mockContext.getHttpInterface();
        if (httpInterface.getSyncDelay() > GlobalConstant.MAX_SYNC_DELAY) {
            throw new BaseException(ErrorCode.SYNC_DELAY_TOO_LARGE);
        }
        try {
            if (httpInterface.getSyncDelay() > 0) {
                Thread.sleep(httpInterface.getSyncDelay());
            }
        } catch (InterruptedException e) {
            throw new BaseException(ErrorCode.UNEXPECTED_ERROR, e);
        }
    }

    private void setResponseHeader(MockContext mockContext) {
        mockContext.getHttpInterface().getResponseHeaderList().forEach(httpHeader ->
                mockContext.getHttpServletResponse().setHeader(httpHeader.getName(), httpHeader.getValue())
        );
    }

    private void writeResponseBody(MockContext mockContext, String body) throws IOException {
        if (StringUtils.isNotBlank(body)) {
            mockContext.getHttpServletResponse().getWriter().write(body);
        }
    }

    private void loadBranchScript(MockContext mockContext, String branchName) {
        RequestType requestType = mockContext.getRequestType();
        Long httpInterfaceId = mockContext.getHttpInterface().getId();

        logger.info("Loading branch script from redis...");
        BranchScript branchScript = redisDao.getBranchScript(requestType, branchName);
        if (branchScript == null) {
            logger.info("Loading branch script from DB...");
            branchScript = httpInterfaceDao.selectBranchScript(httpInterfaceId, branchName);
            if (branchScript == null) {
                throw new BaseException(ErrorCode.HTTP_INTERFACE_BRANCH_SCRIPT_NOT_FOUND);
            }
            redisDao.setBranchScript(requestType, branchName, branchScript);
        }

        mockContext.setBranchScript(branchScript);
    }

    @Override
    public void mock(MockContext mockContext) throws IOException {
        logger.info("HttpSyncMockService start");

        ProfilerRegistry profilerRegistry = ProfilerRegistry.getThreadContextInstance();
        Profiler profiler = profilerRegistry.get(HttpSyncMockService.class.getSimpleName());
        profiler.start("mock sync delay");
        mockSyncDelay(mockContext);

        profiler.start("set resp header");
        setResponseHeader(mockContext);

        HttpInterface httpInterface = mockContext.getHttpInterface();
        String responseBody;
        logger.info("ConfigMode = {}", mockContext.getConfigMode().name());
        switch (mockContext.getConfigMode()) {
            case STATIC:
                responseBody = httpInterface.getResponseBody();
                break;
            case SCRIPT:
                profiler.start("exec sync script");
                profiler.startNested(GroovyService.class.getSimpleName());
                responseBody = groovyService.exec(mockContext, httpInterface.getSyncScript());
                break;
            case SCRIPT_WITH_BRANCH:
                profiler.start("exec branch jump script");
                profiler.startNested(GroovyService.class.getSimpleName());
                String branchName = groovyService.exec(mockContext, httpInterface.getBranchJumpScript());
                logger.info("BranchName = {}", branchName);

                profiler.start("load branch script");
                loadBranchScript(mockContext, branchName);

                profiler.start("exec branch script");
                profiler.startNested(GroovyService.class.getSimpleName());
                responseBody = groovyService.exec(mockContext, mockContext.getBranchScript().getSyncScript());
                break;
            default:
                throw new BaseException(ErrorCode.UNKNOWN_CONFIG_MODE);
        }
        logger.info("ResponseBody = {}", responseBody);
        profiler.start("write resp body");
        writeResponseBody(mockContext, responseBody);
    }
}
