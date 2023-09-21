package com.example.test.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.test.model.User;

import java.util.Calendar;

/**
 * JWT工具类
 */
public class JWTUtil {
    private static String key = "c475eeaec2d16f750c21e53a61884b35";

    /**
     * 获取token
     *
     * @param user 登录用户信息
     * @return token
     */
    public static String getToken(User user) {
        //设置令牌过期时间1小时
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, 1);
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("userid", user.getId())
                .withClaim("username", user.getUsername())
                .withExpiresAt(instance.getTime());//过期时间
        return builder.sign(Algorithm.HMAC256(key));//密钥
    }

    /**
     * 验证token
     *
     * @param token
     * @return 验证通过返回true, 失败返回false
     */
    public static boolean verify(String token) {
        if (token == null || token.trim().equals("")) {
            return false;
        } else {
            try {
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(key)).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(token);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }

    /**
     * 解析token
     *
     * @param token
     * @return 用户信息
     */
    public static User parseToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(key)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Claim id = decodedJWT.getClaim("userid");
        Claim username = decodedJWT.getClaim("username");
        User user = new User();
        user.setId(id.asString());
        user.setUsername(username.asString());
        return user;
    }

}
