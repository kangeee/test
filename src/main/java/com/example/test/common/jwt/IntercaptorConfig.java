package com.example.test.common.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 */
@Configuration
public class IntercaptorConfig implements WebMvcConfigurer {

    @Autowired
    private JWTInterceptor jWTInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jWTInterceptor)
                //拦截的路径
                .addPathPatterns("/**")
                //排除登录、注册接口
                .excludePathPatterns("/login/**", "/register/**","/swagger-resources/**","/webjars/**", "/v2/**", "/swagger-ui.html/**","/**");
    }

}
