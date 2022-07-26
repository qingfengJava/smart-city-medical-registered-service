package com.qingfeng.order.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/24
 */
@Configuration
@MapperScan("com.qingfeng.order.dao")
public class OrderConfig {


    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
