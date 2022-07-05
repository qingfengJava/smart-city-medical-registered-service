package com.qingfeng.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * 定义网关跨域配置类
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/5
 */
@Configuration
public class CrossConfig {

    /**
     * 注意网关配置了跨域类，在控制层就不需要使用@CrossOrigin注解了
     * @return
     */
    @Bean
    public CorsWebFilter corsFilter() {
        //所有的都配置** 允许所有的访问
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

}
