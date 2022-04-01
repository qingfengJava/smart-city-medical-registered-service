package com.qingfeng.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类：统一管理所有的配置注解，就不用写在启动类上了
 *
 * @MapperScan： mapper文件的扫描注解，可以配置在启动类上
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/1
 */
@Configuration
@MapperScan("com.qingfeng.hosp.mapper")
public class HospConfig {
}
