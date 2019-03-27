package com.dxm.anymock.core.biz.impl;

import com.dxm.anymock.common.base.entity.RequestType;
import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.dal.dao.HttpInterfaceDao;
import com.dxm.anymock.common.dal.dao.RedisDao;
import com.dxm.anymock.core.biz.GroovyService;
import com.dxm.anymock.core.biz.HttpSyncMockService;
import com.dxm.anymock.core.biz.entity.HttpMockContext;
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

    private void mockSyncDelay(HttpMockContext httpMockContext) {
        HttpInterface httpInterface = httpMockContext.getHttpInterface();
        if (httpInterface.getSyncDelay() > GlobalConstant.MAX_SYNC_DELAY) {
            throw new BizException(ResultCode.SYNC_DELAY_TOO_LARGE);
        }
        try {
            if (httpInterface.getSyncDelay() > 0) {
                Thread.sleep(httpInterface.getSyncDelay());
            }
        } catch (InterruptedException e) {
            throw new BizException(ResultCode.UNEXPECTED_ERROR, e);
        }
    }

    private void setResponseHeader(HttpMockContext httpMockContext) {
        httpMockContext.getHttpInterface().getResponseHeaderList().forEach(httpHeader ->
                httpMockContext.getHttpServletResponse().setHeader(httpHeader.getName(), httpHeader.getValue())
        );
    }

    private void writeResponseBody(HttpMockContext httpMockContext, String body) throws IOException {
        if (StringUtils.isNotBlank(body)) {
            httpMockContext.getHttpServletResponse().getWriter().write(body);
        }
    }

    private void loadBranchScript(HttpMockContext httpMockContext, String branchName) {
        RequestType requestType = httpMockContext.getRequestType();
        Long httpInterfaceId = httpMockContext.getHttpInterface().getId();

        logger.info("Loading branch script from redis...");
        BranchScript branchScript = redisDao.getBranchScript(requestType, branchName);
        if (branchScript == null) {
            logger.info("Loading branch script from DB...");
            branchScript = httpInterfaceDao.selectBranchScript(httpInterfaceId, branchName);
            if (branchScript == null) {
                throw new BizException(ResultCode.HTTP_INTERFACE_BRANCH_SCRIPT_NOT_FOUND);
            }
            redisDao.setBranchScript(requestType, branchName, branchScript);
        }

        httpMockContext.setBranchScript(branchScript);
    }

    @Override
    public void mock(HttpMockContext httpMockContext) throws IOException {
        logger.info("HttpSyncMockService start");

        // 同步延时
        mockSyncDelay(httpMockContext);

        // 设置响应头
        setResponseHeader(httpMockContext);

        HttpInterface httpInterface = httpMockContext.getHttpInterface();
        String responseBody;
        logger.info("ConfigMode = {}", httpMockContext.getConfigMode().name());

        switch (httpMockContext.getConfigMode()) {
            case STATIC:
                responseBody = httpInterface.getResponseBody();
                break;
            case SCRIPT:
                groovyService.initBinding(httpMockContext);
                groovyService.bindSyncProperty(httpMockContext);
                responseBody = groovyService.exec(httpMockContext, httpInterface.getSyncScript());
                break;
            case SCRIPT_WITH_BRANCH:
                groovyService.initBinding(httpMockContext);
                groovyService.bindSyncProperty(httpMockContext);
                String branchName = groovyService.exec(httpMockContext, httpInterface.getBranchJumpScript());
                logger.info("BranchName = {}", branchName);
                loadBranchScript(httpMockContext, branchName);
                responseBody = groovyService.exec(httpMockContext, httpMockContext.getBranchScript().getSyncScript());
                break;
            default:
                throw new BizException(ResultCode.UNKNOWN_CONFIG_MODE);
        }
        logger.info("ResponseBody = {}", responseBody);
        writeResponseBody(httpMockContext, responseBody);
    }
}
