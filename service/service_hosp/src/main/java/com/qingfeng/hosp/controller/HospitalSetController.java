package com.qingfeng.hosp.controller;

import com.qingfeng.hosp.service.HospitalSetService;
import com.qingfeng.model.model.hosp.HospitalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 医院设置（医院信息）的控制层
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/1
 */
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    /**
     * 查询医院设置表的所有信息
     * @return
     */
    @GetMapping("findAll")
    public List<HospitalSet> findAllHospitalSet(){
        //调用service的方法查询信息   注意： 因为MP对mapper和service都做了封装，所以可以直接调用方法
        List<HospitalSet> list = hospitalSetService.list();
        return list;
    }

    /**
     * 根据Id删除医院设置信息(逻辑删除)
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public boolean removeHospSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        return flag;
    }


}
