package com.qingfeng.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.model.model.user.UserInfo;
import com.qingfeng.model.vo.user.LoginVo;
import com.qingfeng.model.vo.user.UserAuthVo;

import java.util.Map;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/5
 */
public interface UserInfoService  extends IService<UserInfo> {

    /**
     * 用户登录
     * @param loginVo
     * @return
     */
    Map<String, Object> loginUser(LoginVo loginVo);

    /**
     * 根据openid查询信息是否已经存在
     * @param openid
     * @return
     */
    UserInfo selectWxInfoOpenId(String openid);

    /**
     * 用户认证接口
     * @param userId
     * @param userAuthVo
     */
    void userAuth(Long userId, UserAuthVo userAuthVo);
}
