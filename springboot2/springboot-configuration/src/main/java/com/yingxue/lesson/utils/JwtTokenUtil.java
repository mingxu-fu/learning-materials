package com.yingxue.lesson.utils;

import com.yingxue.lesson.entity.TokenSettings;

/**
 * @ClassName: JwtTokenUtil
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public class JwtTokenUtil {

    private static String secretKey;

    private static String issuer;

    public static void setTokenSettings(TokenSettings tokenSettings){
        secretKey=tokenSettings.getSecretKey();
        issuer=tokenSettings.getIssuer();
    }

    public static String getToken(){
        return secretKey+issuer;
    }
}
