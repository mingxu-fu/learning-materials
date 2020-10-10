package com.yingxue.lesson.controller;


import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class tongxunController {


    @PostMapping("/test")
    public String tongxun(@RequestBody HashMap map)  {
        String id = map.get("id").toString();
        System.out.println("***************************"+id);
        if("2017".equals(id)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", "qqq");
            jsonObject.put("name", "xmf");
            jsonObject.put("age", "22");
            jsonObject.put("sex", "male");
            String resp = JSONObject.toJSONString(jsonObject);
            return resp;
        }else{
            return "id 错误";
        }
    }


}
