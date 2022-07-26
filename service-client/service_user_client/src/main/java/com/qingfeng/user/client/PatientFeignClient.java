package com.qingfeng.user.client;

import com.qingfeng.model.model.user.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/24
 */
@FeignClient(value = "service-user")
@Repository
public interface PatientFeignClient {

    /**
     * 获取就诊人信息，根据Id获取
     * @param id
     * @return
     */
    @GetMapping("/api/user/patient/inner/get/{id}")
    Patient getPatientOrder(@PathVariable("id") Long id);
}
