package com.qingfeng.user.service;

import com.qingfeng.model.vo.user.LoginVo;

import java.util.Map;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/5
 */
public interface UserInfoService {

    /**
     * 用户登录
     * @param loginVo
     * @return
     */
    Map<String, Object> loginUser(LoginVo loginVo);
}
