package com.me.test.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author Alfalfa99
 * @Date 2020/9/13 15:54
 * @Version 1.0
 * JWT生成以及校验工具类
 */

@ConfigurationProperties("jwt.config")
@Component
public class JwtUtil {
    private String key;
    private long ttl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    /**
     * 生成JWT
     * @param skey
     * @param openid
     * @return
     */
    public String createJWT(String skey, String openid) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //在这里我们将用户的openid存入Jwt中，方便后续使用
        JwtBuilder builder = Jwts.builder().setId(openid)
                .setIssuedAt(now)
                //在这里我们将用户的角色存入Jwt中，方便后续鉴权,如果想存别的内容也可以往里写
                .signWith(SignatureAlgorithm.HS256, key).claim("skey", skey);
        if (ttl > 0) {
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     *
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }
}
