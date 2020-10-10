package com.yingxue.lesson.web.controller;

import com.yingxue.lesson.service.UserService;
import com.yingxue.lesson.service.impl.yonghu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: TestController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RestController
@RequestMapping(value="/test",method = {RequestMethod.GET,RequestMethod.POST})
public class TestController {
//    @Autowired
//    private UserService userService;
//    @GetMapping("/hello")
//    public String hello(){
//        return userService.testService();
//    }

    @Autowired
    private yonghu yh;
    @PostMapping("/hy")
    public String hy(){
        return yh.test();
    }

}

