package com.qingfeng.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 医院模块的服务
 *
 * 因为Swagger的依赖不在我们这个项目下，所以要配置Swagger的扫描规则
 * @ComponentScan(basePackages = "com.qingfeng")
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/3/31
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.qingfeng")
public class ServiceHospApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class,args);
    }
}
