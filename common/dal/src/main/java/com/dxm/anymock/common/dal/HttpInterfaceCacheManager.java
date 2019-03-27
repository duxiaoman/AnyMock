package com.dxm.anymock.common.dal;

import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import com.dxm.anymock.common.dal.model.HttpInterfaceKeyBO;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class HttpInterfaceCacheManager {

    @Autowired
    private RedisTemplate<String, HttpInterfaceBO> httpInterfaceRedisTemplate;

    private String buildKey(HttpInterfaceKeyBO httpInterfaceKeyBO) {
        return String.format("ANY-MOCK:HTTP-INTERFACE:%s:%s",
                httpInterfaceKeyBO.getRequestMethod(), httpInterfaceKeyBO.getRequestUri());
    }

    public void clear(HttpInterfaceKeyBO httpInterfaceKeyBO) {
        httpInterfaceRedisTemplate.delete(buildKey(httpInterfaceKeyBO));
    }

    public HttpInterfaceBO get(HttpInterfaceKeyBO httpInterfaceKeyBO) {
        String key = buildKey(httpInterfaceKeyBO);
        if (BooleanUtils.isTrue(httpInterfaceRedisTemplate.hasKey(key))) {
            return httpInterfaceRedisTemplate.opsForValue().get(key);
        } else {
            return null;
        }
    }

    public void set(HttpInterfaceBO httpInterfaceBO) {
        String key = buildKey(httpInterfaceBO);
        httpInterfaceRedisTemplate.opsForValue().set(key, httpInterfaceBO);
    }
}
