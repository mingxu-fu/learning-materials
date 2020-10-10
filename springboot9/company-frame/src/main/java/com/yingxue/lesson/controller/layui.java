package com.yingxue.lesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class layui {

    @GetMapping("/layui")
    public String test(){
        return "test";
    }
}
