package com.dxm.anymock.web.service.config;

import com.dxm.anymock.common.base.GlobalConstant;
import com.dxm.anymock.common.base.logger.WebDigestLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                new WebDigestLogger(GlobalConstant.HTTP_TRACE_LOGGER)).addPathPatterns("/**");
    }

    @Bean
    public WebMvcConfigurer crossOriginConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(GlobalConstant.URL_PREFIX_API_V2 + "/**");
            }
        };
    }
}
