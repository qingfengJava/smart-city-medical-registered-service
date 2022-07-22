package com.qingfeng.service.msm.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * InitializingBean: 让项目在加载的时候就初始化
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/5
 */
@Component
public class ConstantTengPropertiesUtils implements InitializingBean {

    /**
     * appId
     */
    @Value("${tengyun.sms.appId}")
    private Integer appId;

    /**
     * appKey
     */
    @Value("${tengyun.sms.appKey}")
    private String appKey;

    /**
     * 短信模板Id
     */
    @Value("${tengyun.sms.templateId}")
    private Integer templateId;

    /**
     * 签名的具体内容
     */
    private String smsSinge = "清风学Java";

    public static Integer APP_ID;
    public static String APP_KEY;
    public static Integer TEMPLATE_ID;
    public static String SMS_SINGE;

    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = appId;
        APP_KEY = appKey;
        TEMPLATE_ID = templateId;
        SMS_SINGE = smsSinge;
    }
}

