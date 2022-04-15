package com.qingfeng.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/5
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.qingfeng"})
@EnableDiscoveryClient
public class ServiceCmnApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCmnApplication.class, args);
    }
}
