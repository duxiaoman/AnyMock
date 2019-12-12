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


package com.dxm.anymock.core.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.ResultCodeTranslator;
import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.base.model.response.BaseResponse;
import com.dxm.anymock.common.base.model.response.DataResponse;
import com.dxm.anymock.common.dal.InterfaceCacheManager;
import com.dxm.anymock.common.dal.model.TcpInterfaceBO;
import com.dxm.anymock.common.dal.model.TcpInterfaceKeyBO;
import com.dxm.anymock.core.biz.service.TcpMockService;
import groovyx.net.http.HttpConfigs;
import org.apache.ibatis.javassist.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jff on 2019/9/6.
 */

@RestController
@RequestMapping("/anymock/")
public class TcpController {

    @Autowired
    private TcpMockService tcpMockService;


    @Autowired
    private ResultCodeTranslator translator;

    private final static Logger logger = LoggerFactory.getLogger(TcpController.class);


    /**
     * 启动tcp服务及端口
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/tcp/startTcpService", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse startTcpService(HttpServletRequest req) {
        try {
            String tcpPort = req.getParameter("tcpPort");

            TcpInterfaceKeyBO tcpInterfaceKeyBO = new TcpInterfaceKeyBO();
            tcpInterfaceKeyBO.setRequestUri(tcpPort);
            TcpInterfaceBO tcpInterfaceBO = tcpMockService.queryTcpInterfaceBO(tcpInterfaceKeyBO);
            logger.info("开启tcp服务,收到入参tcpPort=" + tcpPort + ",获取的tcp接口模型是:" +
                    JSONObject.toJSONString(tcpInterfaceBO));

            tcpMockService.startTcpServer(tcpInterfaceBO);

            return new BaseResponse(translator.translate(ResultCode.SUCCESS));

        } catch (BizException e) {
            logger.error("开启tcp服务发生异常,", e);
            return new BaseResponse(translator.translate(e.getResultCode()));
        } catch (Exception e) {
            logger.error("开启tcp服务发生异常,", e);
            return new BaseResponse(translator.translate(ResultCode.UNEXPECTED_ERROR));
        }
    }

    /**
     * 删除tcp端口及服务
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/tcp/delTcpService", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse delTcpService(HttpServletRequest req) {
        try {
            String tcpPort = req.getParameter("tcpPort");
            TcpInterfaceKeyBO tcpInterfaceKeyBO = new TcpInterfaceKeyBO();
            tcpInterfaceKeyBO.setRequestUri(tcpPort);
            TcpInterfaceBO tcpInterfaceBO = tcpMockService.queryTcpInterfaceBO(tcpInterfaceKeyBO);

            logger.info("删除tcp服务,收到入参tcpPort=" + tcpPort + ",从db中获取的接口模型是:" +
                    JSONObject.toJSONString(tcpInterfaceBO));

            tcpMockService.delTcpServer(tcpInterfaceBO);
            return new BaseResponse(translator.translate(ResultCode.SUCCESS));
        } catch (BizException e) {
            logger.error("删除tcp服务发生异常,", e);
            return new BaseResponse(translator.translate(e.getResultCode()));
        } catch (Exception e) {
            logger.error("删除tcp服务发生异常,", e);
            return new BaseResponse(translator.translate(ResultCode.UNEXPECTED_ERROR));
        }
    }


    /**
     * 更改tcp端口服务的可用状态
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/tcp/changeTcpServerStatus", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            method = RequestMethod.POST)
    public BaseResponse changeTcpServerStatus(HttpServletRequest req) {
        try {
            String tcpPort = req.getParameter("tcpPort");
            String start = req.getParameter("isStart");
            boolean isStart = Boolean.valueOf(start);
            TcpInterfaceKeyBO tcpInterfaceKeyBO = new TcpInterfaceKeyBO();
            tcpInterfaceKeyBO.setRequestUri(tcpPort);
            TcpInterfaceBO tcpBO = tcpMockService.queryTcpInterfaceBO(tcpInterfaceKeyBO);
            logger.info(
                    "变更tcp服务状态,收到入参tcpPort=" + tcpPort + ",isStart=" + isStart + ",获取的接口模型是:" +
                            JSONObject.toJSONString(tcpBO));

            boolean suc = tcpMockService.changeTcpServerStatus(tcpBO, isStart);
            if (suc) {
                return new BaseResponse(translator.translate(ResultCode.SUCCESS));
            } else {
                return new BaseResponse(translator.translate(ResultCode.UNEXPECTED_ERROR));
            }
        } catch (BizException e) {
            logger.error("变更tcp服务状态发生异常,", e);
            return new BaseResponse(translator.translate(e.getResultCode()));
        } catch (Exception e) {
            logger.error("变更tcp服务状态发生异常,", e);
            return new BaseResponse(translator.translate(ResultCode.UNEXPECTED_ERROR));
        }
    }

    /**
     * 修改接口后更新tcp端口及服务
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/tcp/updateTcpService", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updateTcpService(HttpServletRequest req) {
        try {
            String oldTcpPort = req.getParameter("oldTcpPort");
            String newTcpPort = req.getParameter("newTcpPort");

            tcpMockService.delTcpServer(oldTcpPort);
            TcpInterfaceKeyBO tcpInterfaceKeyBO = new TcpInterfaceKeyBO();
            tcpInterfaceKeyBO.setRequestUri(newTcpPort);
            TcpInterfaceBO tcpBO = tcpMockService.queryTcpInterfaceBO(tcpInterfaceKeyBO);
            logger.info("更新tcp服务,收到入参newTcpPort=" + newTcpPort + ",获取的接口模型是:" + JSONObject.toJSONString(tcpBO));

            if (tcpBO.getStart()) {
                tcpMockService.startTcpServer(tcpBO);
            }
            return new BaseResponse(translator.translate(ResultCode.SUCCESS));

        } catch (BizException e) {
            logger.error("更新tcp服务发生异常,", e);
            return new BaseResponse(translator.translate(e.getResultCode()));
        } catch (Exception e) {
            logger.error("更新tcp服务发生异常,", e);
            return new BaseResponse(translator.translate(ResultCode.UNEXPECTED_ERROR));
        }
    }

}
