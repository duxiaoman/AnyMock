package com.dxm.anymock.common.dal.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.dxm.anymock.common.base.entity.BranchScript;
import com.dxm.anymock.common.base.entity.HttpInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Autowired
    private LettuceConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTemplate<String, HttpInterface> httpInterfaceRedisTemplate() {
        RedisTemplate<String, HttpInterface> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(new FastJsonRedisSerializer<>(String.class));
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(HttpInterface.class));
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, BranchScript> branchScriptRedisTemplate() {
        RedisTemplate<String, BranchScript> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(new FastJsonRedisSerializer<>(String.class));
        redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<>(BranchScript.class));
        return redisTemplate;
    }
}
