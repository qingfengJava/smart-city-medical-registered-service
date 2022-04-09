package com.qingfeng.hosp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 清风学Java
 */
@SpringBootApplication
@MapperScan("com.qingfeng.hosp.mapper")
public class HospitalManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalManageApplication.class, args);
    }

}
