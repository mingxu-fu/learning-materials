package com.yingxue.lesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ShouyeController {
    @GetMapping("/")
    public String tiao(){
        return "index";
    }


}
