/*
*  Copyright 2018-2019 Duxiaoman Group Holding Ltd.

*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at

*  http://www.apache.org/licenses/LICENSE-2.0

*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.

*/


package com.dxm.anymock.manager.integration.client.impl;

import com.dxm.anymock.common.base.ResultCodeTranslator;
import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.base.model.response.BaseResponse;
import com.dxm.anymock.manager.integration.client.TcpServiceClient;
import com.dxm.anymock.manager.integration.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


/**
 * Created by jff on 2019/9/6.
 */
@Service
public class TcpServiceClientImpl implements TcpServiceClient {

    @Value("${anymock.core.url-prefix}")
    private String anymockServiceProvider;

    private final static Logger logger = LoggerFactory.getLogger(TcpServiceClientImpl.class);

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void startTcpService(String tcpPort) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("tcpPort", tcpPort);
        BaseResponse response =
                restTemplate.postForObject(anymockServiceProvider + Constants.START_TCP_SERVICE_URL, params, BaseResponse.class);
        if (response != null && !response.getResultCode().equals("000000")) {
            throw new BizException(ResultCode.START_TCP_ERROR);
        }
    }


    @Override
    public void deleteTcpService(String tcpPort) {
        BaseResponse response;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("tcpPort", tcpPort);
        logger.info("向anymockcore发起tcp服务移除请求,tcpPort=" + tcpPort);

        response = restTemplate.postForObject(anymockServiceProvider
                + Constants.DEL_TCP_SERVICE_URL, params, BaseResponse.class);

        logger.info("anymockcore返回tcp服务移除结果:" + response);
        if (response != null && !response.getResultCode().equals("000000")) {
            throw new RuntimeException(response.getResultMsg());
        }

    }


    @Override
    public boolean changeTcpServerStatus(String tcpPort, boolean isStart) {
        BaseResponse response;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("tcpPort", tcpPort);
        params.add("isStart", Boolean.toString(isStart));

        logger.info("向anymock发起变更tcp服务状态的请求,tcpPort=" + tcpPort + ",isStart=" + isStart);

        response = restTemplate.postForObject(anymockServiceProvider
                + Constants.CHANGE_TCP_SERVICE_STATUS_URL, params, BaseResponse.class);

        logger.info("anymock-core返回变更tcp服务状态的结果:" + response);

        return response != null && StringUtils.equals(response.getResultCode(), "000000") ? true : false;
    }

    @Override
    public void updateTcpService(String oldTcpPort, String newTcpPort) {
        BaseResponse response;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("oldTcpPort", oldTcpPort);
        params.add("newTcpPort", newTcpPort);

        response = restTemplate.postForObject(anymockServiceProvider
                + Constants.UPDATE_TCP_SERVICE_URL, params, BaseResponse.class);
        if (response != null && !response.getResultCode().equals("000000")) {
            throw new BizException(ResultCode.UNEXPECTED_ERROR);
        }

    }
}
