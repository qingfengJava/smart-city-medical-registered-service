package com.qingfeng.smart.jwt;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/7/5
 */
public class JwtHelper {

    /**
     * 设置token的过期时间   毫秒
     */
    private static long tokenExpiration = 24*60*60*1000;
    /**
     * token的签名秘钥
     */
    private static String tokenSignKey = "qingfengjava";

    /**
     * 根据字符串生成一个字符串
     * @param userId
     * @param userName
     * @return
     */
    public static String createToken(Long userId, String userName) {
        String token = Jwts.builder()
                //分类
                .setSubject("SERVICE-USER")
                //设置过期时间  当前时间+设置的时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                //主体信息
                .claim("userId", userId)
                .claim("userName", userName)
                //签名hash
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    /**
     * 根据token字符串得到用户id
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        //解析token
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
    }

    /**
     * 根据token字符串得到用户名称
     * @param token
     * @return
     */
    public static String getUserName(String token) {
        if(StringUtils.isEmpty(token)) {
            return "";
        }
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("userName");
    }

    /**
     * 测试方法
     * @param args
     */
    public static void main(String[] args) {
        String token = JwtHelper.createToken(1L, "lucy");
        System.out.println(token);
        System.out.println(JwtHelper.getUserId(token));
        System.out.println(JwtHelper.getUserName(token));
    }
}

