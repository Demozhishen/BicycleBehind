package com.springcloud.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * jwtУ�鹤����
 */
public class JwtUtil {

    //��Ч��Ϊ
    public static final Long JWT_TTL = 3600000L;// 60 * 60 *1000  һ��Сʱ
    //������Կ����
    public static final String JWT_KEY = "itcast";

    /**
     * ���ɼ��ܺ����Կ secretKey
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    /**
     * ����
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
