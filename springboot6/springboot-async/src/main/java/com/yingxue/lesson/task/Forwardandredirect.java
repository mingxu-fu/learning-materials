package com.yingxue.lesson.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/api")
public class Forwardandredirect {

//    转发
    @GetMapping("/baidu")
    public ModelAndView baidu(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:/api/par?name=fumingxu");
        return mv;
    }

//重定向
    @GetMapping("/par")
    public String demo3(@RequestParam String name){
        System.out.println(name);
        return "redirect:http://www.baidu.com";
    }
}
