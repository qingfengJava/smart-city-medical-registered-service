package com.qingfeng.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.model.model.hosp.HospitalSet;
import com.qingfeng.model.vo.order.SignInfoVo;

/**
 * 医院设置的业务层接口
 *
 * IService<HospitalSet>：MP提供的业务层接口：对应的泛型是医院设置的实体类
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/1
 */
public interface HospitalSetService extends IService<HospitalSet> {

    /**
     * 根据传递过来的医院编码，查询数据库，查询签名
     * @param hoscode
     * @return
     */
    String getSingKey(String hoscode);

    /**
     * 获取医院签名信息
     * @param hoscode
     * @return
     */
    SignInfoVo getSignInfoVo(String hoscode);
}
