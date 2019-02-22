package com.dxm.anymock.web.service.config;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.logger.BasicDigestLogger;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicDigestConfig {

    @Bean
    public Advisor dalDigestLogInterceptor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        BasicDigestLogger basicDigestLogger = new BasicDigestLogger(GlobalConstant.DAL_DIGEST_LOGGER);
        advisor.setExpression("within(com.dxm.anymock.common.dal.dao..*)");
        advisor.setAdvice(basicDigestLogger);
        return advisor;
    }

    @Bean
    public Advisor apiDigestLogInterceptor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        BasicDigestLogger basicDigestLogger = new BasicDigestLogger(GlobalConstant.API_DIGEST_LOGGER);
        advisor.setExpression("within(com.dxm.anymock.web.service.controller..*)");
        advisor.setAdvice(basicDigestLogger);
        return advisor;
    }
}
