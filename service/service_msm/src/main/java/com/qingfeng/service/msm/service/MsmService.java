package com.qingfeng.service.msm.service;

import com.qingfeng.model.vo.msm.MsmVo;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/5
 */
public interface MsmService {

    /**
     * 阿里云服务发送验证码
     * @param phone
     * @param code
     * @return
     */
    boolean sendCodeByAli(String phone, String code);

    /**
     * 发送邮箱的服务
     * @param phone
     * @param code
     * @return
     */
    boolean sendEmail(String phone, String code);

    /**
     * 腾讯云服务发送短信
     * @param phone
     * @param code
     * @return
     */
    boolean sendCodeByTeng(String phone, String code);

    /**
     * 使用mq发送短信
     * @param msmVo
     * @return
     */
    boolean send(MsmVo msmVo);

    /**
     * 阿里云限额5条短信
     * @param phone
     * @param code
     */
    /*boolean sendMsmCodeByAliApi(String phone, String code);*/
}
