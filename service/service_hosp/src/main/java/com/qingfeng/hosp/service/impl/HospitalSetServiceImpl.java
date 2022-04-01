package com.qingfeng.hosp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.hosp.mapper.HospitalSetMapper;
import com.qingfeng.hosp.service.HospitalSetService;
import com.qingfeng.model.model.hosp.HospitalSet;
import org.springframework.stereotype.Service;

/**
 * 医院设置的业务层接口实现
 *
 * ServiceImpl<HospitalSetMapper, HospitalSet> ：MP提供的业务层接口：对应的泛型是相应的接口和实体
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/1
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

    //注意： 使用ServiceImpl之后，MP已经自动帮助我们注入好了对应的持久层接口（dao/mapper）

}
