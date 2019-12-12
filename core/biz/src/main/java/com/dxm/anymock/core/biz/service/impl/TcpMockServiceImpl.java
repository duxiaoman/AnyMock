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


package com.dxm.anymock.core.biz.service.impl;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.dal.InterfaceCacheManager;
import com.dxm.anymock.common.dal.dao.TcpInterfaceDao;
import com.dxm.anymock.common.dal.model.TcpInterfaceBO;
import com.dxm.anymock.common.dal.model.TcpInterfaceKeyBO;
import com.dxm.anymock.core.biz.service.TcpMockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jff on 2019/8/23.
 */
@Service
public class TcpMockServiceImpl implements TcpMockService, InitializingBean {


    /* 流程日志 */
    private static final Logger logger = LoggerFactory.getLogger(TcpMockServiceImpl.class);

    @Autowired
    private TcpServer tcpServer;

    @Autowired
    private InterfaceCacheManager interfaceCacheManager;

    @Autowired
    private TcpInterfaceDao tcpInterfaceDao;


    /**
     * 启动tcp服务
     *
     * @param tcpInterfaceBO
     */
    @Override
    public void startTcpServer(TcpInterfaceBO tcpInterfaceBO) {
        if (null == tcpInterfaceBO) {
            return;
        }

        if (tcpInterfaceBO.getStart()) {
            boolean res = tcpServer.start(tcpInterfaceBO);

            if (!res) {
                throw new RuntimeException("START tcp:" + tcpInterfaceBO.getRequestUri() + "失败!");
            }
        }
    }


    @Override
    public boolean changeTcpServerStatus(TcpInterfaceBO tcpInterfaceBO, Boolean isStart) {
        boolean suc = true;
        try {
            if (null == tcpInterfaceBO) {
                return false;
            }

            if (isStart) {
                suc = tcpServer.start(tcpInterfaceBO);
            } else {
                suc = tcpServer.stop(tcpInterfaceBO);
            }

        } catch (Exception e) {
            logger.error(" tcp server start/stop fail");
        }
        return suc;
    }

    @Override
    public boolean delTcpServer(TcpInterfaceBO tcpInterfaceBO) {
        boolean suc = false;
        try {
            if (null == tcpInterfaceBO) {
                return false;
            }
            suc = tcpServer.stop(tcpInterfaceBO);
        } catch (Exception e) {
            logger.error("tcp server close fail", e);
        }
        return suc;
    }


    @Override
    public boolean delTcpServer(String tcpPort) {
        boolean suc = false;
        try {
            if (null == tcpPort) {
                return false;
            }
            suc = tcpServer.stop(tcpPort);
        } catch (Exception e) {
            logger.error("tcp server close fail", e);
        }
        return suc;
    }

    @Override
    public TcpInterfaceBO queryTcpInterfaceBO(TcpInterfaceKeyBO tcpInterfaceKeyBO) {
        logger.info("Loading TCP interface from redis...");
        TcpInterfaceBO tcpInterfaceBO = interfaceCacheManager.get(tcpInterfaceKeyBO);
        if (tcpInterfaceBO == null) {
            logger.info("Loading TCP interface from DB...");
            tcpInterfaceBO = tcpInterfaceDao.queryByKey(tcpInterfaceKeyBO);
            if (tcpInterfaceBO == null) {
                throw new BizException(ResultCode.NOT_FOUND_TCP_INTERFACE);
            }
            interfaceCacheManager.set(tcpInterfaceBO);
        }

        return tcpInterfaceBO;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("------------初始化tcp接口开始-------------");
        List<TcpInterfaceBO> tcpInterfaceBOs = tcpInterfaceDao.getAllTcpInterface();
        if (tcpInterfaceBOs != null) {
            tcpInterfaceBOs.forEach(tcpInterfaceBO -> {
                try {
                    if (tcpInterfaceBO.getStart()) {
                        tcpServer.start(tcpInterfaceBO);
                        logger.info("TCP端口:{}启动成功", tcpInterfaceBO.getRequestUri());

                    }
                } catch (Exception e) {
                    logger.warn("TCP端口:{}启动发生异常!", tcpInterfaceBO.getRequestUri());
                }

            });
        }
        logger.info("------------初始化tcp接口结束-------------");
    }
}
