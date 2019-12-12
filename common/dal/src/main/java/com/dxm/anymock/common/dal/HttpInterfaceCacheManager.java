package com.dxm.anymock.common.dal;

import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.common.dal.model.HttpInterfaceKeyBO;
import com.dxm.anymock.common.dal.model.TcpInterfaceBO;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class HttpInterfaceCacheManager {

    @Autowired
    private RedisTemplate<String, HttpInterfaceBO> httpInterfaceRedisTemplate;

    /* 流程日志 */
    private static final Logger logger = LoggerFactory.getLogger(HttpInterfaceCacheManager.class);

    private String buildKey(HttpInterfaceKeyBO httpInterfaceKeyBO) {
        return String.format("ANY-MOCK:HTTP-INTERFACE:%s:%s",
                httpInterfaceKeyBO.getRequestMethod(), httpInterfaceKeyBO.getRequestUri());
    }

    public void clear(HttpInterfaceKeyBO httpInterfaceKeyBO) {
        httpInterfaceRedisTemplate.delete(buildKey(httpInterfaceKeyBO));
    }

    public HttpInterfaceBO get(HttpInterfaceKeyBO httpInterfaceKeyBO) {
        try {
            String key = buildKey(httpInterfaceKeyBO);
            if (BooleanUtils.isTrue(httpInterfaceRedisTemplate.hasKey(key))) {
                return httpInterfaceRedisTemplate.opsForValue().get(key);
            } else {
                logger.info("根据url {}, query HTTPInterface from redis, 不存在", httpInterfaceKeyBO.getRequestUri());
                return null;
            }
        } catch (Exception e) {
            logger.warn("根据url {}, query HTTPInterface from redis error,{}",
                    httpInterfaceKeyBO.getRequestUri(), e.getMessage());
            return null;
        }

    }

    public void set(HttpInterfaceBO httpInterfaceBO) {
        try {
            String key = buildKey(httpInterfaceBO);
            httpInterfaceRedisTemplate.opsForValue().set(key, httpInterfaceBO);
        } catch (Exception e) {

        }

    }
}
