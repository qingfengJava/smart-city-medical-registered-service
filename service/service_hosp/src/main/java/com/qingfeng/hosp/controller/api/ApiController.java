package com.qingfeng.hosp.controller.api;

import com.qingfeng.hosp.service.HospitalService;
import com.qingfeng.hosp.service.HospitalSetService;
import com.qingfeng.model.model.hosp.Hospital;
import com.qingfeng.smart.exception.GlobalException;
import com.qingfeng.smart.helper.HttpRequestHelper;
import com.qingfeng.smart.result.Result;
import com.qingfeng.smart.result.ResultCodeEnum;
import com.qingfeng.smart.utils.MD5;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 上传医院的控制层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/10
 */
@RestController
@ApiOperation(value = "提供上传医院的控制层相关接口",tags = "上传医院的接口")
@RequestMapping("/api/hosp")
@CrossOrigin
public class ApiController {

    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private HospitalSetService hospitalSetService;

    @ApiOperation("上传医院接口")
    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request){
        //获取到传递过来的医院信息
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
        //获取到传递过来的医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        //将数据进行转换
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //获取医院编号
        String hoscode = (String) paramMap.get("hoscode");
        //1、获取医院系统传递过来的签名  签名进行了Md5加密
        String hospSign = (String) paramMap.get("sign");
        //2、根据传递过来的医院系统的编码，查询数据库，进行签名
        String singKey = hospitalSetService.getSingKey(hoscode);
        //3、把数据库查询出来的签名进行MD5加密
        String singKeyMd5 = MD5.encrypt(singKey);
        //4、比较两个签名是否一致
        if (!hospSign.equals(singKeyMd5)){
            throw new GlobalException(ResultCodeEnum.SIGN_ERROR);
        }

        //调用service层的方法实现根据医院编号查询
        Hospital hospital = hospitalService.getByHoscode(hoscode);
        return Result.ok(hospital);
    }

}
