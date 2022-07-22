package com.qingfeng.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.model.model.user.Patient;

import java.util.List;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/22
 */
public interface PatientService extends IService<Patient> {

    /**
     * 获取就诊人列表
     * @param userId
     * @return
     */
    List<Patient> findAllUserId(Long userId);

    /**
     * 根据Id获取就诊人信息
     * @param id
     * @return
     */
    Patient getPatientId(Long id);
}
