package com.qingfeng.service.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.github.qcloudsms.SmsSingleSender;
import com.qingfeng.model.vo.msm.MsmVo;
import com.qingfeng.service.msm.service.MsmService;
import com.qingfeng.service.msm.utils.ConstantPropertiesUtils;
import com.qingfeng.service.msm.utils.ConstantTengPropertiesUtils;
import com.qingfeng.service.msm.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/5
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Autowired
    private EmailUtils emailUtils;

    /**
     * 阿里云服务免费发送验证码
     *
     * @param phone
     * @param code
     * @return
     */
    @Override
    public boolean sendCodeByAli(String phone, String code) {
        //判断手机号是否为空
        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        //整合阿里云的短信服务  设置相关的参数
        DefaultProfile profile = DefaultProfile.
                getProfile(ConstantPropertiesUtils.REGION_Id,
                        ConstantPropertiesUtils.ACCESS_KEY_ID,
                        ConstantPropertiesUtils.SECRECT);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //手机号
        request.putQueryParameter("PhoneNumbers", phone);
        //签名名称
        request.putQueryParameter("SignName", "我的谷粒在线教育网站");
        //模板code
        request.putQueryParameter("TemplateCode", "SMS_180051135");
        //验证码  使用json格式   {"code":"123456"}
        Map<String, Object> param = new HashMap(10);
        param.put("code", code);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        //调用方法进行短信发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sendEmail(String phone, String code) {
        int i = emailUtils.sendEmail(phone, "【智慧医疗预约挂号服务平台】", "你本次登录或注册的验证码是：" + code+"\r\n注意：有效时间5分钟！！！");
        return i == 1 ? true : false;
    }

    @Override
    public boolean sendCodeByTeng(String phone, String code) {
        try {
            String[] params = {code,Integer.toString(5)};
            SmsSingleSender sender = new SmsSingleSender(ConstantTengPropertiesUtils.APP_ID, ConstantTengPropertiesUtils.APP_KEY);
            //封装发送信息并返回结果
            sender.sendWithParam("86",phone,ConstantTengPropertiesUtils.TEMPLATE_ID,params,ConstantTengPropertiesUtils.SMS_SINGE,"","");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Mq发送短信的封装
     * @param msmVo
     * @return
     */
    @Override
    public boolean send(MsmVo msmVo) {
        if(!StringUtils.isEmpty(msmVo.getPhone())) {

            Map<String, Object> param = msmVo.getParam();
            String content = param.get("title")+"\r\n"+param.get("amount")+"\r\n"+param.get("reserveDate")+"\r\n"+param.get("name")+"\r\n"+param.get("quitTime");

            int i = emailUtils.sendEmail(msmVo.getPhone(), "【智慧医疗预约挂号服务平台】", content);

            return i == 1 ? true : false;
        }
        return false;

    }

    /**
     * 阿里云限额5条短信
     * @param phone
     * @param code
     */
    /*@Override
    public boolean sendMsmCodeByAliApi(String phone, String code){
        String host = "https://dxyzm.market.alicloudapi.com";
        String path = "/chuangxin/dxjk";
        String method = "POST";
        //开通服务后 买家中心-查看AppCode
        String appcode = "434ea90b7f774dbaa6ecb4e521057e06";
        Map<String, String> headers = new HashMap<>(10);
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<>(10);
        querys.put("content", "【创信】本次登录的验证码是："+code+"，2分钟内有效！");
        querys.put("mobile", phone);
        Map<String, String> bodys = new HashMap<>(10);

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            HttpEntity entity = response.getEntity();
            System.out.println(entity);
            //获取response的body
            return EntityUtils.toString(response.getEntity()).contains("Success");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }*/
}
