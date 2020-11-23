package com.example.testspring.utils;

import com.example.testspring.model.userModel.LightUserEntity;
import com.alibaba.fastjson.JSON;

public class LightTokenUtil
{


    private static JwtHelper jwtWorker = new JwtHelper();

    public static LightUserEntity getSubject(String jwtToken) {
        String subject = jwtWorker.getClaims(jwtToken).getSubject();
        return JSON.parseObject(subject, LightUserEntity.class);
    }

    /**
     * 验证token
     * @param jwtToken
     * @return
     */
    public static boolean verify(String jwtToken) {
        return jwtWorker.verify(jwtToken);
    }

    public static String createJwtDefaultExp(LightUserEntity entity) {
        String subject = generalSubject(entity);
        return jwtWorker.createJwtDefaultExp(subject);
    }

    /**
     * 无过期时间颁发
     * @param entity
     * @return
     */
    public static String createJwtNeverExp(LightUserEntity entity) {
        String subject = generalSubject(entity);
        return jwtWorker.createJwtNeverExp(subject);
    }

    private static String generalSubject(LightUserEntity entity) {
        return JSON.toJSONString(entity);
    }
}
