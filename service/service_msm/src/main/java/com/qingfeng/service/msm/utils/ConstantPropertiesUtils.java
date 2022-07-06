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
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${aliyun.sms.regionId}")
    private String regionId;

    /**
     * id
     */
    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;

    /**
     * 秘钥
     */
    @Value("${aliyun.sms.secret}")
    private String secret;

    public static String REGION_Id;
    public static String ACCESS_KEY_ID;
    public static String SECRECT;

    @Override
    public void afterPropertiesSet() throws Exception {
        REGION_Id = regionId;
        ACCESS_KEY_ID = accessKeyId;
        SECRECT = secret;
    }
}

