package com.yingxue.lesson.utils;

import com.yingxue.lesson.entity.TokenSettings;
import org.springframework.stereotype.Component;

/**
 * @ClassName: StaticInitializerUtil
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Component
public class StaticInitializerUtil {

    private TokenSettings tokenSettings;
    public StaticInitializerUtil(TokenSettings tokenSettings) {
        JwtTokenUtil.setTokenSettings(tokenSettings);
    }
}
