package com.yingxue.lesson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName: TestController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RequestMapping("/api")
@RestController
public class TestController {

    @GetMapping("/user/filter")
    public String hello(){
        return "我被myFilter过滤器监控了";
    }

    @GetMapping("/home/open/aaa/bbb/ccc/info")
    public String getHome(){
        return "欢迎访问首页";
    }
    @GetMapping("/open/unLogin")
    public String getUnLogin(){
        return "登录失效，请重新登录";
    }

    @GetMapping("/test")
    public String test(){
        return "------------test-------------";
    }
}
