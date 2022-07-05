package com.qingfeng.hosp.controller;

import com.qingfeng.hosp.service.DepartmentService;
import com.qingfeng.model.vo.hosp.DepartmentVo;
import com.qingfeng.smart.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 医院科室控制层
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/4
 */
@RestController
@Api(value = "提供医院管理的相关服务", tags = "医院科室管理接口")
@RequestMapping("/admin/hosp/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation("根据Id查询医院所有科室列表")
    @GetMapping("getDeptList/{hoscode}")
    public Result getDeptList(@PathVariable String hoscode){
        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
        return Result.ok(list);
    }
}
