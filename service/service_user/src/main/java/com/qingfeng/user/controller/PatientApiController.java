package com.qingfeng.user.controller;

import com.qingfeng.model.model.user.Patient;
import com.qingfeng.smart.result.Result;
import com.qingfeng.smart.utils.AuthContextHolder;
import com.qingfeng.user.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/22
 */
@Api(value = "提供就诊人管理接口", tags = "就诊人管理接口")
@RestController
@RequestMapping("/api/user/patient")
public class PatientApiController {

    @Autowired
    private PatientService patientService;

    @ApiOperation("获取就诊人列表")
    @GetMapping("auth/findAll")
    public Result findAll(HttpServletRequest request) {
        //获取当前登录用户id
        Long userId = AuthContextHolder.getUserId(request);
        List<Patient> list = patientService.findAllUserId(userId);
        return Result.ok(list);
    }

    @ApiOperation("添加就诊人")
    @PostMapping("auth/save")
    public Result savePatient(@RequestBody Patient patient, HttpServletRequest request) {
        //获取当前登录用户id
        Long userId = AuthContextHolder.getUserId(request);
        patient.setUserId(userId);
        patientService.save(patient);
        return Result.ok();
    }

    @ApiOperation("根据Id获取就诊人信息")
    @GetMapping("auth/get/{id}")
    public Result getPatient(@PathVariable Long id) {
        Patient patient = patientService.getPatientId(id);
        return Result.ok(patient);
    }

    @ApiOperation("修改就诊人")
    @PostMapping("auth/update")
    public Result updatePatient(@RequestBody Patient patient) {
        patientService.updateById(patient);
        return Result.ok();
    }

    @ApiOperation("删除就诊人")
    @DeleteMapping("auth/remove/{id}")
    public Result removePatient(@PathVariable Long id) {
        patientService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据就诊人Id获取就诊人信息")
    @GetMapping("inner/get/{id}")
    public Patient getPatientOrder(
            @ApiParam(name = "id", value = "就诊人id", required = true)
            @PathVariable("id") Long id) {
        return patientService.getPatientId(id);
    }

}
