package com.yingxue.lesson.controller;

import com.yingxue.lesson.model.UserInfo;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: MockMvcController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/test")
public class MockMvcController {

    @GetMapping("/user")
    public UserInfo getUser(@RequestParam(required = false) String name,@RequestParam(required = false) String userId){
        UserInfo userInfo=new UserInfo();
        userInfo.setName(name);
        userInfo.setUserId(userId);
        return userInfo;
    }
    @PostMapping("/user2")
    public UserInfo getUser(@RequestBody UserInfo userInfo){
        return userInfo;
    }
}
