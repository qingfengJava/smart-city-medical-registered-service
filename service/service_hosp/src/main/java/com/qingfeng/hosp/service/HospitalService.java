package com.qingfeng.hosp.service;

import com.qingfeng.model.model.hosp.Hospital;

import java.util.Map;

/**
 * 上传医院的业务层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/10
 */
public interface HospitalService {

    /**
     * 添加医院接口
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 根据医院编号查询医院信息
     * @param hoscode
     * @return
     */
    Hospital getByHoscode(String hoscode);
}
