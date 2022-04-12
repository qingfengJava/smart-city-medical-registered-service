package com.qingfeng.hosp.controller.api;

import com.qingfeng.hosp.service.DepartmentService;
import com.qingfeng.hosp.service.HospitalService;
import com.qingfeng.hosp.service.HospitalSetService;
import com.qingfeng.hosp.service.ScheduleService;
import com.qingfeng.model.model.hosp.Department;
import com.qingfeng.model.model.hosp.Hospital;
import com.qingfeng.model.model.hosp.Schedule;
import com.qingfeng.model.vo.hosp.DepartmentQueryVo;
import com.qingfeng.model.vo.hosp.ScheduleQueryVo;
import com.qingfeng.smart.exception.GlobalException;
import com.qingfeng.smart.helper.HttpRequestHelper;
import com.qingfeng.smart.result.Result;
import com.qingfeng.smart.result.ResultCodeEnum;
import com.qingfeng.smart.utils.MD5;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 医院管理的控制层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/10
 */
@RestController
@ApiOperation(value = "提供医院管理的控制层相关接口",tags = "医院管理的接口")
@RequestMapping("/api/hosp")
@CrossOrigin
public class ApiController {

    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private HospitalSetService hospitalSetService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation("上传医院接口")
    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request){
        Map<String, Object> paramMap = getStringObjectMap(request);
        //传输过程中 “+”转换为“ ”，因此我们要转换回来
        String logoData = (String) paramMap.get("logoData");
        logoData = logoData.replaceAll(" ","+");
        paramMap.put("logoData",logoData);

        //调用service层的方法
        hospitalService.save(paramMap);
        return Result.ok();
    }

    @ApiOperation("查询医院")
    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request){
        Map<String, Object> paramMap = getStringObjectMap(request);
        //调用service层的方法实现根据医院编号查询
        String hoscode = (String) paramMap.get("hoscode");
        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }

    @ApiOperation("上传科室")
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request){
        Map<String, Object> paramMap = getStringObjectMap(request);

        //调用service层添加的方法
        departmentService.save(paramMap);
        return Result.ok();
    }

    @ApiOperation("查询科室信息")
    @PostMapping("department/list")
    public Result findDepartment(HttpServletRequest request){
        //获取传递过来的信息并校验
        Map<String, Object> paramMap = getStringObjectMap(request);

        //医院编号
        String hoscode = (String) paramMap.get("hoscode");
        //当前页 和 每页记录数
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String) paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 5 : Integer.parseInt((String) paramMap.get("limit"));

        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);

        //调用service层的方法查询医院科室信息
        Page<Department> pageMode = departmentService.findPageDepartment(page,limit,departmentQueryVo);
        return Result.ok(pageMode);
    }

    @ApiOperation("删除科室信息")
    @PostMapping("department/remove")
    public Result removeDepartment(HttpServletRequest request){
        Map<String, Object> paramMap = getStringObjectMap(request);
        //获取医院编号
        String hoscode = (String) paramMap.get("hoscode");
        //获取科室编号
        String depcode = (String) paramMap.get("depcode");
        //根据医院编号和科室编号删除科室信息
        departmentService.remove(hoscode,depcode);
        return Result.ok();
    }

    @ApiOperation("上传医院排班")
    @PostMapping("/saveSchedule")
    public Result saveSchedule(HttpServletRequest request){
        Map<String, Object> paramMap = getStringObjectMap(request);

        scheduleService.save(paramMap);
        return Result.ok();
    }

    @ApiOperation("查询医院排班")
    @PostMapping("/schedule/list")
    public Result findSchedule(HttpServletRequest request){
        Map<String, Object> paramMap = getStringObjectMap(request);
        //获取医院编号
        String hoscode = (String) paramMap.get("hoscode");
        //获取科室编号
        String depcode = (String) paramMap.get("depcode");
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String) paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 5 : Integer.parseInt((String) paramMap.get("limit"));
        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode(depcode);
        Page<Schedule> pageMode = scheduleService.findPageSchedule(page,limit,scheduleQueryVo);
        return Result.ok(pageMode);
    }

    @ApiOperation("删除医院排班")
    @PostMapping("/schedule/remove")
    public Result removeSchedule(HttpServletRequest request){
        Map<String, Object> paramMap = getStringObjectMap(request);
        //获取医院编号
        String hoscode = (String) paramMap.get("hoscode");
        //获取排班编号
        String hosSchecodeId = (String) paramMap.get("hosSchecodeId");

        scheduleService.remove(hoscode,hosSchecodeId);
        return Result.ok();
    }



    private Map<String, Object> getStringObjectMap(HttpServletRequest request) {
        //获取到传递过来的信息
        Map<String, String[]> requestMap = request.getParameterMap();
        //将数据进行转换
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //1、获取医院系统传递过来的签名  签名进行了Md5加密
        String hospSign = (String) paramMap.get("sign");
        //2、根据传递过来的医院系统的编码，查询数据库，进行签名
        String hoscode = (String) paramMap.get("hoscode");
        String singKey = hospitalSetService.getSingKey(hoscode);
        //3、把数据库查询出来的签名进行MD5加密
        String singKeyMd5 = MD5.encrypt(singKey);
        //4、比较两个签名是否一致
        if (!hospSign.equals(singKeyMd5)){
            throw new GlobalException(ResultCodeEnum.SIGN_ERROR);
        }
        return paramMap;
    }

}
