package com.yingxue.lesson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: InterceptorController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/api")
public class InterceptorController {

    @GetMapping("/home/open/info")
    public String home(){
        return "欢迎来到首页";
    }

    @GetMapping("/user/interceptor")
    public String interceptor(){
        return "我被拦截了并通过了拦截器";
    }

    @GetMapping("/open/unLogin")
    public String getUnLogin(){
        return "登录失效，请重新登录";
    }
}
