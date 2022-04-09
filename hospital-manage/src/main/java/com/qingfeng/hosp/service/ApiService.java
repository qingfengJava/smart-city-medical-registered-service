package com.qingfeng.hosp.service;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * @author 清风学Java
 */
public interface ApiService {

    String getHoscode();

    String getSignKey();

    JSONObject getHospital();

    /**
     * 添加医院信息
     * @param data
     * @return
     */
    boolean saveHospital(String data);

    Map<String, Object> findDepartment(int pageNum, int pageSize);

    boolean saveDepartment(String data);

    boolean removeDepartment(String depcode);

    Map<String, Object> findSchedule(int pageNum, int pageSize);

    boolean saveSchedule(String data);

    boolean removeSchedule(String hosScheduleId);

    void  saveBatchHospital() throws IOException;
}
