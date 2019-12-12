package com.dxm.anymock.core.web.config;

import com.dxm.anymock.common.base.interceptor.HttpTracer;
import com.dxm.anymock.common.base.interceptor.MdcManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MdcManager()).addPathPatterns("/**/*");
        registry.addInterceptor(new HttpTracer()).addPathPatterns("/**/*");
    }

    @Bean
    public WebMvcConfigurer crossOriginConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**/*");
            }
        };
    }
}
