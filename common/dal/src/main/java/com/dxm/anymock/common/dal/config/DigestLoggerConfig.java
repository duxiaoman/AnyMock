package com.dxm.anymock.common.dal.config;

import com.dxm.anymock.common.base.interceptor.DigestLogger;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DigestLoggerConfig {
    @Bean
    public Advisor digestLogger() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("within(com.dxm.anymock.common.dal.dao..*)");
        advisor.setAdvice(new DigestLogger());
        return advisor;
    }
}
