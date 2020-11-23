package com.example.testspring.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.SimpleIdGenerator;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import com.example.testspring.req.LightUapException;
import com.example.testspring.req.LightErrorCode;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.SimpleIdGenerator;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


@Slf4j

public class JwtHelper {


    // 参考 https://blog.csdn.net/m0_37809141/article/details/86572697
    // 签发者
    private final String ISSUER = "lightCloudServiceCenter";

    private final String SECRET_CODE = "lightSecret";

    // 时间 Token类型 说明
    // 0-10分钟 正常Token 正常访问
    // 10-15分钟 濒死Token 正常访问，返回新Token，建议使用新Token
    // >15分钟 过期Token 拒绝访问

    // 过期时间30分钟
    public final static long EXPIRE_TIME = 30 * 60 * 1000;

    // 延长时间5分钟
    public final static long ADVANCE_EXPIRE_TIME = 5 * 60 * 1000;

    /**
     * <pre>
     *  验证token是否失效
     *  true:过期   false:没过期
     * </pre>
     */
    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getClaims(token).getExpiration();
            // null 表示永不过期
            return expiration != null && expiration.before(new Date());
        }
        catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }

    /**
     * 判断token是否非法
     * @param token token
     * @return 未过期返回true，否则返回false
     */
    public boolean verify(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 获取可用的token 如该用户当前token可用，即返回 当前token不可用，则返回一个新token
     * @return
     */
    public String getGoodToken(String token) {
        // 校验当前token能否使用，不能使用则生成新token
        if (this.checkToken(token)) {
            return token;
        }
        else {
            Claims claims = this.getClaims(token);
            // 初始化新token
            return this.createJwtDefaultExp(claims.getSubject());
        }
    }

    /**
     * 无过期时间颁发
     * @param subject
     * @return
     */
    public String createJwtNeverExp(String subject) {
        return createJwt(ISSUER, subject, -1, "");
    }

    /**
     * 5分钟
     * @param subject
     * @return
     */
    public String createJwtDefaultExp(String subject) {
        return createJwt(ISSUER, subject, EXPIRE_TIME, "");
    }

    /**
     * 有过期时间颁发
     * @param subject
     * @param ttlMillis
     * @return
     */
    public String createJwtWithExp(String subject, long ttlMillis) {
        return createJwt(ISSUER, subject, ttlMillis, "");
    }

    /**
     * 一个JWT实际上就是一个字符串，它由三部分组成，头部(Header)、载荷(Payload)与签名(Signature)
     * @param issuer 该JWT的签发者，是否使用是可选的
     * @param subject 该JWT所面向的用户，是否使用是可选的
     * @param ttlMillis 什么时候过期，这里是一个Unix时间戳，是否使用是可选的
     * @param audience 接收该JWT的一方，是否使用是可选的
     * @return
     */
    public String createJwt(String issuer, String subject, long ttlMillis, String audience) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Key signingKey = new SecretKeySpec(this.getSecretKey(), signatureAlgorithm.getJcaName());
        SimpleIdGenerator idGenerator = new SimpleIdGenerator();

        JwtBuilder jwtBuilder = Jwts.builder().setId(idGenerator.generateId().toString()).setSubject(subject)
                .setIssuedAt(now).setIssuer(issuer).setAudience(audience).signWith(signatureAlgorithm, signingKey);
        // 设置Token的过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            jwtBuilder.setExpiration(exp);
        }

        return jwtBuilder.compact();

    }

    // 私钥解密token信息
    public Claims getClaims(String jwtToken) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(jwtToken).getBody();
            if (claims != null) {
                return claims;
            }
            else {
                throw new LightUapException(LightErrorCode.FORBIDDEN);
            }
        }
        catch (ExpiredJwtException e) {
            log.error("JWT凭证已经过期, {}", e.getMessage());
            throw new LightUapException(LightErrorCode.FORBIDDEN);
        }
        catch (JwtException e) {
            e.printStackTrace();
            throw new LightUapException(LightErrorCode.FORBIDDEN);
        }
    }

    /**
     * 刷新 token的过期时间 如该用户当前token可用，即返回 当前token不可用，则返回一个新token
     * @return
     */
    public String refreshToken(String token) {

        boolean flag = checkToken(token);
        // 校验当前token能否使用，不能使用则生成新token
        if (flag) {
            return token;
        }
        else {
            String subject = this.getClaims(token).getSubject();
            // 初始化新token
            return createJwtDefaultExp(subject);
        }
    }

    /**
     * 检查当前token是否还能继续使用 true：可以 false：不建议
     * @param token
     * @return
     */
    public boolean checkToken(String token) {
        try {
            // jwt正常情况 则判断失效时间是否大于5分钟
            long expireTime = getClaims(token).getExpiration().getTime();
            long diff = expireTime - System.currentTimeMillis();
            // 如果有效期小于5分钟，则不建议继续使用该token
            if (diff < ADVANCE_EXPIRE_TIME) {
                return false;
            }
        }
        catch (Exception e) {
            throw new LightUapException(LightErrorCode.FORBIDDEN);
        }
        return true;
    }

    private byte[] getSecretKey() {
        return DatatypeConverter.parseBase64Binary(SECRET_CODE);
    }

}
