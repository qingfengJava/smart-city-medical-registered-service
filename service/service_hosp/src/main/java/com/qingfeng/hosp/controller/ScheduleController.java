package com.qingfeng.hosp.controller;

import com.qingfeng.hosp.service.ScheduleService;
import com.qingfeng.model.model.hosp.Schedule;
import com.qingfeng.smart.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 医院排班管理
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/4
 */
@RestController
@Api(value = "提供医院排班管理的相关接口", tags = "医院排班管理接口")
@RequestMapping("/admin/hosp/schedule")
@CrossOrigin
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    /**
     *
     * @param page
     * @param limit
     * @param hoscode
     * @param depcode
     * @return
     */
    @ApiOperation("分页查询排班规则数据")
    @GetMapping("getScheduleRule/{page}/{limit}/{hoscode}/{depcode}")
    public Result getScheduleRule(@PathVariable long page,
                                  @PathVariable long limit,
                                  @PathVariable String hoscode,
                                  @PathVariable String depcode){
        Map<String,Object> map = scheduleService.getRuleSchedule(page,limit,hoscode,depcode);
        return Result.ok(map);
    }

    /**
     * 根据医院编号，科室编号和工作日期，查询排班详情信息
     * @param hoscode
     * @param depcode
     * @param workDate
     * @return
     */
    @ApiOperation("查询排班详细信息")
    @GetMapping("getScheduleDetail/{hoscode}/{depcode}/{workDate}")
    public Result getScheduleDetail(@PathVariable String hoscode,
                                    @PathVariable String depcode,
                                    @PathVariable String workDate){
        List<Schedule> list = scheduleService.getDetailSchedule(hoscode,depcode,workDate);
        return Result.ok(list);
    }
}
