package com.qingfeng.hosp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingfeng.hosp.mapper.HospitalSetMapper;
import com.qingfeng.hosp.service.HospitalSetService;
import com.qingfeng.model.model.hosp.HospitalSet;
import com.qingfeng.model.vo.order.SignInfoVo;
import com.qingfeng.smart.exception.GlobalException;
import com.qingfeng.smart.result.ResultCodeEnum;
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


    /**
     * 根据传递过来的医院编码，查询数据库，查询签名
     * @param hoscode
     * @return
     */
    @Override
    public String getSingKey(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode", hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        return hospitalSet.getSignKey();
    }

    /**
     * 获取医院签名信息
     * @param hoscode
     * @return
     */
    @Override
    public SignInfoVo getSignInfoVo(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
        if(null == hospitalSet) {
            throw new GlobalException(ResultCodeEnum.HOSPITAL_OPEN);
        }
        SignInfoVo signInfoVo = new SignInfoVo();
        signInfoVo.setApiUrl(hospitalSet.getApiUrl());
        signInfoVo.setSignKey(hospitalSet.getSignKey());
        return signInfoVo;

    }

}
