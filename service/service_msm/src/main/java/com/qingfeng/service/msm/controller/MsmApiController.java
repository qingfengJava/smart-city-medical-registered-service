package com.qingfeng.service.msm.controller;

import com.qingfeng.service.msm.service.MsmService;
import com.qingfeng.service.msm.utils.RandomUtil;
import com.qingfeng.smart.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/5
 */
@RestController
@Api(value = "提供短信服务接口",tags = "短信服务")
@RequestMapping("/api/msm")
public class MsmApiController {

    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 发送手机验证码
     */
    @ApiOperation("发送登录注册验证码")
    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone){
        //从redis中获取验证码，如果获取到，返回OK
        //key：手机号   value：验证码
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return Result.ok();
        }

        //如果从redis中获取不到，生成验证码，通过整合阿里云短信服务进行发送
        code = RandomUtil.getSixBitRandom();
        //调用service方法，判断是电话还是邮箱，使用不同的通知方式
        boolean isSend = false;
        if (phone.contains("@")){
            //说明是邮箱
            isSend = msmService.sendEmail(phone,code);
        }else{
           isSend = msmService.sendCodeByAli(phone,code);
        }


        //生成验证码放到redis里面，设置有效时间
        if (isSend){
            //将验证码存入redis，2分钟内有效
            redisTemplate.opsForValue().set(phone,code,2, TimeUnit.MINUTES);
            return Result.ok();
        }else{
            return Result.fail().message("验证码发送失败");
        }
    }
}
