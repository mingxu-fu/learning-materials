package com.yingxue.lesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: ThyemeleafController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Controller
public class ThymeleafController {

    @GetMapping("/thymeleaf")
    public String thrmeleaf(Model model){
        model.addAttribute("username","zhangsan");
        return "index";
    }
}
