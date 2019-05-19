package com.atguigu.gmall.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    public static void main(String[] args) {
        String salt = "127.0.0.1";
        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("userId", "2");
        stringStringMap.put("nickName", "博哥");
        stringStringMap.put("payload", "11");
        String token = encode("atguigu0328", null, salt);

        System.out.println(token);

        Map usermap = decode("atguigu0328", token, salt);

        System.out.println(usermap);
    }

    /**
     * jwt加密
     * @param key 系统当中独一无二的不会外泄的秘钥
     * @param map 登录信息
     * @param salt 连接服务器的浏览器的一些特征值，比如，类型、IP、名称等。
     * @return
     */
    public static String encode(String key, Map map, String salt){
        if (salt != null){
            key += salt;
        }

        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256, key);
        jwtBuilder.setPayload("aa");
        jwtBuilder.addClaims(map);

        String token = jwtBuilder.compact();
        return token;
    }

    public static Map decode(String key, String token, String salt){
        if (salt != null) {
            key += salt;
        }
        Claims map = null;
        map = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

        System.out.println("map.toString()=" + map.toString());

        return map;
    }
}
