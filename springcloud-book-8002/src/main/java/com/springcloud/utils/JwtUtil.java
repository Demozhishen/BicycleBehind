package com.springcloud.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * jwtУ�鹤����
 */
public class JwtUtil {

    //��Ч��Ϊ
    public static final Long JWT_TTL = 3600000L;// 60 * 60 *1000  һ��Сʱ
    //������Կ����
    public static final String JWT_KEY = "itcast";

    public static String createJWT(String id, String subject, Long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        SecretKey secretKey = generalKey();

        JwtBuilder builder = Jwts.builder()
                .setId(id)              //Ψһ��ID
                .setSubject(subject)   // ����  ������JSON����
                .setIssuer("admin")     // ǩ����
                .setIssuedAt(now)      // ǩ��ʱ��
                .signWith(signatureAlgorithm, secretKey) //ʹ��HS256�ԳƼ����㷨ǩ��, �ڶ�������Ϊ��Կ
                .setExpiration(expDate);// ���ù���ʱ��
        return builder.compact();
    }

    /**
     * ���ɼ��ܺ����Կ secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
}