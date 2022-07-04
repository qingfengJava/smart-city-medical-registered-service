package com.qingfeng.hosp.controller;

import com.qingfeng.hosp.service.HospitalService;
import com.qingfeng.model.model.hosp.Hospital;
import com.qingfeng.model.vo.hosp.HospitalQueryVo;
import com.qingfeng.smart.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 医院的控制层
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/13
 */
@RestController
@Api(value = "提供医院的相关服务", tags = "医院接口")
@RequestMapping("/admin/hosp/hospital")
@CrossOrigin
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @ApiOperation("分页条件查询医院列表")
    @GetMapping("list/{page}/{limit}")
    public Result listHosp(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> pageModel = hospitalService.selectHospPage(page, limit, hospitalQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("更新医院上线状态")
    @GetMapping("updateHospStatus/{id}/{status}")
    public Result updateHospStatus(@PathVariable("id") String id, @PathVariable("status") Integer status) {
        hospitalService.updateHospStatus(id, status);
        return Result.ok();
    }

    @ApiOperation("医院详情信息")
    @GetMapping("showHospDetail/{id}")
    public Result showHospDetail(@PathVariable("id") String id){
        Map<String, Object> map = hospitalService.getHospById(id);
        return Result.ok(map);
    }
}
