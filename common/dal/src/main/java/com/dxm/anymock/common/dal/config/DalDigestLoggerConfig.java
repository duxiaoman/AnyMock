package com.dxm.anymock.common.dal.config;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.logger.BasicDigestLogger;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DalDigestLoggerConfig {
    @Bean
    public Advisor dalDigestLogger() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        BasicDigestLogger basicDigestLogger = new BasicDigestLogger(GlobalConstant.DAL_DIGEST_LOGGER);
        advisor.setExpression("within(com.dxm.anymock.common.dal.dao..*)");
        advisor.setAdvice(basicDigestLogger);
        return advisor;
    }
}
