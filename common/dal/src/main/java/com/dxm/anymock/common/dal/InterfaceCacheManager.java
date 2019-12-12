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


package com.dxm.anymock.common.dal;

import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.common.dal.model.HttpInterfaceKeyBO;
import com.dxm.anymock.common.dal.model.TcpInterfaceBO;
import com.dxm.anymock.common.dal.model.TcpInterfaceKeyBO;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by jff on 2019/8/29.
 */
@Component
public class InterfaceCacheManager {

    @Autowired
    private RedisTemplate<String, HttpInterfaceBO> httpInterfaceRedisTemplate;

    @Autowired
    private RedisTemplate<String, TcpInterfaceBO> tcpInterfaceRedisTemplate;

    /* 流程日志 */
    private static final Logger logger = LoggerFactory.getLogger(InterfaceCacheManager.class);

    private String buildKey(HttpInterfaceKeyBO httpInterfaceKeyBO) {
        return String.format("ANY-MOCK:HTTP-INTERFACE:%s:%s",
                httpInterfaceKeyBO.getRequestMethod(), httpInterfaceKeyBO.getRequestUri());
    }

    private String buildKey(TcpInterfaceKeyBO tcpInterfaceKeyBO) {
        return String.format("ANY-MOCK:TCP-INTERFACE:%s", tcpInterfaceKeyBO.getRequestUri());
    }

    public void clear(HttpInterfaceKeyBO httpInterfaceKeyBO) {
        httpInterfaceRedisTemplate.delete(buildKey(httpInterfaceKeyBO));
    }

    public void clear(TcpInterfaceKeyBO tcpInterfaceKeyBO) {
        tcpInterfaceRedisTemplate.delete(buildKey(tcpInterfaceKeyBO));
    }

    public HttpInterfaceBO get(HttpInterfaceKeyBO httpInterfaceKeyBO) {
        try {
            String key = buildKey(httpInterfaceKeyBO);
            if (BooleanUtils.isTrue(httpInterfaceRedisTemplate.hasKey(key))) {
                return httpInterfaceRedisTemplate.opsForValue().get(key);
            } else {
                logger.info("根据url:{},method:{} query HTTPInterface from redis, 不存在",
                        httpInterfaceKeyBO.getRequestUri(), httpInterfaceKeyBO.getRequestMethod());
                return null;
            }
        } catch (Exception e) {
            logger.warn("根据url {}, query HTTPInterface from redis error,{}",
                    httpInterfaceKeyBO.getRequestUri(), e.getMessage());
            return null;
        }

    }

    public TcpInterfaceBO get(TcpInterfaceKeyBO tcpInterfaceKeyBO) {
        try {
            String key = buildKey(tcpInterfaceKeyBO);
            if (BooleanUtils.isTrue(tcpInterfaceRedisTemplate.hasKey(key))) {
                return tcpInterfaceRedisTemplate.opsForValue().get(key);
            } else {
                logger.info("根据url {}, query TcpInterface from redis, 不存在", tcpInterfaceKeyBO.getRequestUri());
                return null;
            }
        } catch (Exception e) {
            logger.warn("根据url {}, query TcpInterface from redis error,{}",
                    tcpInterfaceKeyBO.getRequestUri(), e.getMessage());
            return null;
        }
    }


    public TcpInterfaceBO getTcp(String port) {
        try {
            String key = String.format("ANY-MOCK:TCP-INTERFACE:%s", port);
            if (BooleanUtils.isTrue(tcpInterfaceRedisTemplate.hasKey(key))) {
                return tcpInterfaceRedisTemplate.opsForValue().get(key);
            } else {
                logger.info("根据url {}, query TcpInterface from redis, 不存在", port);
                return null;
            }
        } catch (Exception e) {
            logger.warn("根据url {}, query TcpInterface from redis error,{}",
                    port, e.getMessage());
            return null;
        }
    }

    public void set(HttpInterfaceBO httpInterfaceBO) {
        try {
            String key = buildKey(httpInterfaceBO);
            httpInterfaceRedisTemplate.opsForValue().set(key, httpInterfaceBO);
        } catch (Exception e) {
            logger.error("set redis error:", e);
        }
    }

    public void set(TcpInterfaceBO tcpInterfaceBO) {
        try {
            String key = buildKey(tcpInterfaceBO);
            tcpInterfaceRedisTemplate.opsForValue().set(key, tcpInterfaceBO);
        } catch (Exception e) {
            logger.error("set redis error:", e);
        }
    }
}
