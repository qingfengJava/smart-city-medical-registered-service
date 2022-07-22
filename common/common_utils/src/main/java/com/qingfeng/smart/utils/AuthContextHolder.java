package com.qingfeng.smart.utils;

import com.qingfeng.smart.jwt.JwtHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取当前用户信息的工具类
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/21
 */
public class AuthContextHolder {

    /**
     * 获取当前用户id
     * @param request
     * @return
     */
    public static Long getUserId(HttpServletRequest request) {
        //从header获取token
        String token = request.getHeader("token");
        //jwt从token获取userid
        Long userId = JwtHelper.getUserId(token);
        return userId;
    }

    /**
     * 获取当前用户名称
     * @param request
     * @return
     */
    public static String getUserName(HttpServletRequest request) {
        //从header获取token
        String token = request.getHeader("token");
        //jwt从token获取userid
        String userName = JwtHelper.getUserName(token);
        return userName;
    }

}
