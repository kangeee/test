package com.example.test.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * springboot配置类
 *
 * @author dougang
 */
@Configuration
public class SpringBootConfig {

    /**
     * RestTemplate工具类
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        return page;
    }

    /**
     * pageHelper分页插件
     */
//	@Bean
//	public PageHelper getPageHelper() {
//		PageHelper pageHelper = new PageHelper();
//		Properties properties = new Properties();
//		properties.setProperty("helperDialect", "mysql");
//		properties.setProperty("reasonable", "true");
//		properties.setProperty("supportMethodsArguments", "true");
//		properties.setProperty("params", "count=countSql");
//		pageHelper.setProperties(properties);
//		return pageHelper;
//	}

}
