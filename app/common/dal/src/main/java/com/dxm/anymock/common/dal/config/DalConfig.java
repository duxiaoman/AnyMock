package com.dxm.anymock.common.dal.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.dxm.anymock.common.base.entity.BranchScript;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@MapperScan("com.dxm.anymock.common.dal.mapper")
public class DalConfig {
    @Bean
    @ConfigurationProperties("datasource")
    public HikariDataSource hikariDataSource() {
        return new HikariDataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(hikariDataSource());
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:META-INF/mappers/**/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisStandaloneConfiguration());
    }

    @Bean
    @ConfigurationProperties("redis")
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        return new RedisStandaloneConfiguration();
    }

    @Bean
    public RedisTemplate<String, HttpInterface> httpInterfaceRedisTemplate() {
        RedisTemplate<String, HttpInterface> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setDefaultSerializer(new FastJsonRedisSerializer<>(String.class));
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(HttpInterface.class));
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, BranchScript> branchScriptRedisTemplate() {
        RedisTemplate<String, BranchScript> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setDefaultSerializer(new FastJsonRedisSerializer<>(String.class));
        redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<>(BranchScript.class));
        return redisTemplate;
    }
}
