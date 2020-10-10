package com.yingxue.lesson.service.impl;

import com.yingxue.lesson.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String testService() {
        return "testService";
    }
}
