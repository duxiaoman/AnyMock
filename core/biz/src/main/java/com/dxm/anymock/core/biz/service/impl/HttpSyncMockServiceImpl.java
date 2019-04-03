package com.dxm.anymock.core.biz.service.impl;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.base.exception.SysException;
import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.common.dal.model.HttpInterfaceBranchBO;
import com.dxm.anymock.common.dal.model.enums.ConfigMode;
import com.dxm.anymock.core.biz.Delayer;
import com.dxm.anymock.core.biz.service.GroovyService;
import com.dxm.anymock.core.biz.service.HttpSyncMockService;
import com.dxm.anymock.core.biz.HttpMockContext;
import groovy.lang.Binding;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.dxm.anymock.common.dal.model.enums.ConfigMode.GROOVY;
import static com.dxm.anymock.common.dal.model.enums.ConfigMode.GROOVY_TEMPLATE_SWITCH_CASE;
import static com.dxm.anymock.common.dal.model.enums.ConfigMode.TEXT;

@Service
public class HttpSyncMockServiceImpl implements HttpSyncMockService {

    private static final Logger logger = LoggerFactory.getLogger(HttpSyncMockServiceImpl.class);

    @Autowired
    private GroovyService groovyService;

    private HttpInterfaceBranchBO findBranch(String branchName, List<HttpInterfaceBranchBO> branchBOList) {
        for (HttpInterfaceBranchBO branchBO : branchBOList) {
            if (StringUtils.equals(branchBO.getName(), branchName)) {
                return branchBO;
            }
        }
        return null;
    }

    private Binding buildSyncBinding(HttpServletRequest request, HttpServletResponse response) {
        Binding binding = new Binding();
        binding.setProperty("request", request);
        binding.setProperty("response", response);
        return binding;
    }

    @Override
    public void mock(HttpMockContext context, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 同步延时
        HttpInterfaceBO httpInterfaceBO = context.getHttpInterfaceBO();
        Delayer.delay(httpInterfaceBO.getSyncDelay());
        logger.info("Sync delay finished");

        // 设置响应头
        httpInterfaceBO.getResponseHeaderList().forEach(httpHeader
                -> response.setHeader(httpHeader.getName(), httpHeader.getValue()));
        logger.info("Static HTTP response header setting finished.");

        // 配置模式
        ConfigMode configMode = httpInterfaceBO.getConfigMode();
        logger.info("ConfigMode = {}", configMode);

        // Groovy脚本 绑定变量
//        if (configMode == GROOVY || configMode == GROOVY_TEMPLATE_SWITCH_CASE) {
//            Binding binding = new Binding();
//            binding.setProperty("request", request);
//            binding.setProperty("response", response);
//        }

        String responseBody;
        if (configMode == TEXT) {
            responseBody = httpInterfaceBO.getResponseBody();
        } else if (configMode == GROOVY) {
            Binding binding = buildSyncBinding(request, response);
            responseBody = groovyService.exec(binding, httpInterfaceBO.getSyncScript());
        } else if (configMode == GROOVY_TEMPLATE_SWITCH_CASE) {
            Binding binding = buildSyncBinding(request, response);
            String branchName = groovyService.exec(binding, httpInterfaceBO.getBranchJumpScript());
            logger.info("BranchName = {}", branchName);
            HttpInterfaceBranchBO branchBO = findBranch(branchName, httpInterfaceBO.getBranchScriptList());
            if (branchBO == null) {
                throw new BizException(ResultCode.NOT_FOUND_HTTP_INTERFACE_BRANCH);
            }
            context.setHttpInterfaceBranchBO(branchBO);

            responseBody = groovyService.exec(binding, branchBO.getSyncScript());
        } else {
            throw new SysException("Unknown ConfigMode");
        }
        logger.info("ResponseBody = {}", responseBody);

        if (StringUtils.isNotBlank(responseBody)) {
            response.getWriter().write(responseBody);
        }
    }
}
