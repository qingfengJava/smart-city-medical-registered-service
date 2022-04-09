package com.qingfeng.hosp.service;

import java.util.Map;

/**
 * @author 清风学Java
 */
public interface HospitalService {

    /**
     * 预约下单
     * @param paramMap
     * @return
     */
    Map<String, Object> submitOrder(Map<String, Object> paramMap);

    /**
     * 更新支付状态
     * @param paramMap
     */
    void updatePayStatus(Map<String, Object> paramMap);

    /**
     * 更新取消预约状态
     * @param paramMap
     */
    void updateCancelStatus(Map<String, Object> paramMap);


}
