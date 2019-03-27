package com.dxm.anymock.manager.web.config;

import com.dxm.anymock.common.base.interceptor.MdcManager;
import com.dxm.anymock.manager.biz.security.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.dxm.anymock.manager.web.WebConstants.URL_PREFIX_API_V2_PATTERN;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${fe.path}")
    private String fePath;

    @Value("${anymock.manager.admin.password}")
    private String password;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MdcManager()).addPathPatterns(URL_PREFIX_API_V2_PATTERN);
        registry.addInterceptor(new SecurityInterceptor(password)).addPathPatterns(URL_PREFIX_API_V2_PATTERN);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/fe/**").addResourceLocations(fePath);
    }

    @Bean
    public WebMvcConfigurer crossOriginConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(URL_PREFIX_API_V2_PATTERN);
            }
        };
    }
}
