package com.qingfeng.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/5
 */
@Configuration
@MapperScan("com.qingfeng.user.mapper")
public class UserConfig {
}
