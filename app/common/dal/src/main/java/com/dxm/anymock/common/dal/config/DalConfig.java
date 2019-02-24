package com.dxm.anymock.common.dal.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.entity.BranchScript;
import com.dxm.anymock.common.base.entity.HttpInterface;
import com.dxm.anymock.common.base.logger.BasicDigestLogger;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@MapperScan("com.dxm.anymock.common.dal.mapper")
public class DalConfig {

    @Autowired
    private HikariDataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:META-INF/mappers/**/*.xml"));
        return sessionFactory.getObject();
    }

    //@Bean
    //public LettuceConnectionFactory redisConnectionFactory() {
    //    return new LettuceConnectionFactory();
    //}

    @Autowired
    private LettuceConnectionFactory redisConnectionFactory;

    //@Autowired
    //private RedisStandaloneConfiguration redisStandaloneConfiguration;

    /*@Bean
    @ConfigurationProperties("spring.redis")
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        return new RedisStandaloneConfiguration();
    }*/

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

    @Bean
    public Advisor dalDigestLogger() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        BasicDigestLogger basicDigestLogger = new BasicDigestLogger(GlobalConstant.DAL_DIGEST_LOGGER);
        advisor.setExpression("within(com.dxm.anymock.common.dal.dao..*)");
        advisor.setAdvice(basicDigestLogger);
        return advisor;
    }
}
