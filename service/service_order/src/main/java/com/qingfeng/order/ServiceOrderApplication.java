package com.qingfeng.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/24
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.qingfeng"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.qingfeng"})
public class ServiceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
}

