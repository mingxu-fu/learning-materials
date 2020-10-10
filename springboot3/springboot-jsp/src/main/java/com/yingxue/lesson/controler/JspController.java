package com.yingxue.lesson.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: JspController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Controller
public class JspController {
    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
