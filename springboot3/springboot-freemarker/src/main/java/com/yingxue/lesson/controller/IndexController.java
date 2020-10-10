package com.yingxue.lesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: IndexController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("username","lisi");
        return "index";
    }
}
