package com.qingfeng.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingfeng.model.model.user.UserInfo;
import com.qingfeng.model.vo.user.LoginVo;
import com.qingfeng.model.vo.user.UserAuthVo;
import com.qingfeng.model.vo.user.UserInfoQueryVo;

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

    /**
     * 用户列表（条件查询带分页）
     * @param pageParam
     * @param userInfoQueryVo
     * @return
     */
    IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);

    /**
     * 前台用户锁定
     * @param userId
     * @param status
     */
    void lock(Long userId, Integer status);

    /**
     * 前台用户详情
     * @param userId
     * @return
     */
    Map<String, Object> show(Long userId);

    /**
     * 前台用户认证审批
     * @param userId
     * @param authStatus
     */
    void approval(Long userId, Integer authStatus);
}
