package com.dxm.anymock.common.dal.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.dxm.anymock.common.dal.model.HttpInterfaceBO;
import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Autowired
    private LettuceConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTemplate<String, HttpInterfaceBO> httpInterfaceRedisTemplate() {
        RedisTemplate<String, HttpInterfaceBO> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(new FastJsonRedisSerializer<>(String.class));
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(HttpInterfaceBO.class));
        return redisTemplate;
    }
}
