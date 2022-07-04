package com.qingfeng.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingfeng.hosp.service.HospitalSetService;
import com.qingfeng.model.model.hosp.HospitalSet;
import com.qingfeng.model.vo.hosp.HospitalSetQueryVo;
import com.qingfeng.smart.result.Result;
import com.qingfeng.smart.utils.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * 医院设置（医院信息）的控制层
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/1
 */
@RestController
@Api(value = "提供医院设置层的相关功能接口",tags = "医院设置层的接口")
@RequestMapping("/admin/hosp/hospitalSet")
@CrossOrigin
public class HospitalSetController {

    private HospitalSetService hospitalSetService;

    @Autowired
    public HospitalSetController(HospitalSetService hospitalSetService) {
        this.hospitalSetService = hospitalSetService;
    }

    @ApiOperation("查询所有医院设置信息")
    @GetMapping("findAll")
    public Result findAllHospitalSet(){
        //调用service的方法查询信息   注意： 因为MP对mapper和service都做了封装，所以可以直接调用方法
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    @ApiOperation("根据Id删除医院设置信息")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        if (flag){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation("医院设置条件查询带分页")
    @PostMapping("findPage/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){
        //创建Mp的page对象，传递当前页，每页记录数
        Page<HospitalSet> page = new Page<>(current, limit);
        //构建条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        //医院名称
        String hosname = hospitalSetQueryVo.getHosname();
        //医院编号
        String hoscode = hospitalSetQueryVo.getHoscode();
        //做条件查询要判断是否已经有这个条件了
        if (!StringUtils.isEmpty(hosname)){
            //不为空   医院名称要模糊查询
            wrapper.like("hosname", hosname);
        }
        if (!StringUtils.isEmpty(hoscode)){
            //不为空  医院编号要相等查询
            wrapper.eq("hoscode", hoscode);
        }

        //调用方法实现分页查询
        Page<HospitalSet> pageHospitalSet = hospitalSetService.page(page, wrapper);

        //返回结果
        return Result.ok(pageHospitalSet);
    }

    @ApiOperation(("添加医院设置信息"))
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        //设置医院设置的状态  1：使用   0：不能使用
        hospitalSet.setStatus(1);
        //设置签名秘钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));

        //调用service添加的方法（MP中的）
        boolean save = hospitalSetService.save(hospitalSet);
        if(save){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation("根据id获取医院设置信息")
    @GetMapping("getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    @ApiOperation("根据Id修改医院的设置")
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if (flag){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation("批量删除医院设置")
    @DeleteMapping("batchRemove")
    public Result batchRemoveHospitalSet(@RequestBody List<String> idList){
        boolean flag = hospitalSetService.removeByIds(idList);
        if (flag){
            return Result.ok();
        }else{
            return Result.fail("网络异常，删除失败！！！");
        }
    }

    @ApiOperation("医院设置锁定和解锁")
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status){
        //根据id查询医院设置信息
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        //设置状态
        hospitalSet.setStatus(status);
        //调用方法进行修改
        boolean flag = hospitalSetService.updateById(hospitalSet);
        if (flag){
            return Result.ok("医院已锁定！");
        }else {
            return Result.fail("网络异常，操作失败！");
        }
    }

    @ApiOperation("发送签名秘钥")
    @PutMapping("sendKey/{id}")
    public Result sendKeyHospitalSet(@PathVariable Long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //TODO 发送短信   后面通过短信进行完善
        return Result.ok();
    }


}
