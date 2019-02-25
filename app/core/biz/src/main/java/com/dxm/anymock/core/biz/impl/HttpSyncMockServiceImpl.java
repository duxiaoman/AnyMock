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

    private BranchScript loadBranchScript(RequestType requestType, String branchName, Long httpInterfaceId) {
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
        return branchScript;
    }

    @Override
    public void mock(MockContext mockContext) throws IOException {
        logger.info("HttpSyncMockService start");
        mockContext.getProfiler().start("mockSyncDelay");
        mockSyncDelay(mockContext);
        mockContext.getProfiler().start("setResponseHeader");
        setResponseHeader(mockContext);

        mockContext.getProfiler().start("mockContext.getHttpInterface");
        HttpInterface httpInterface = mockContext.getHttpInterface();
        String responseBody;
        logger.info("ConfigMode = {}", mockContext.getConfigMode().name());
        switch (mockContext.getConfigMode()) {
            case STATIC:
                responseBody = httpInterface.getResponseBody();
                break;
            case SCRIPT:
                responseBody = groovyService.exec(mockContext, httpInterface.getSyncScript());
                break;
            case SCRIPT_WITH_BRANCH:
                mockContext.getProfiler().start("branchName = groovyService.exec");
                String branchName = groovyService.exec(mockContext, httpInterface.getBranchJumpScript());
                logger.info("BranchName = {}", branchName);
                mockContext.getProfiler().start("loadBranchScript");
                BranchScript branchScript = loadBranchScript(
                        mockContext.getRequestType(), branchName, mockContext.getHttpInterface().getId());
                mockContext.setBranchScript(branchScript);
                mockContext.getProfiler().start("groovyService.exec");
                responseBody = groovyService.exec(mockContext, branchScript.getSyncScript());
                break;
            default:
                throw new BaseException(ErrorCode.UNKNOWN_CONFIG_MODE);
        }
        logger.info("ResponseBody = {}", responseBody);
        mockContext.getProfiler().start("writeResponseBody");
        writeResponseBody(mockContext, responseBody);
        mockContext.getProfiler().stop();
        logger.warn(mockContext.getProfiler().toString());
    }
}
