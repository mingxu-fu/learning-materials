package com.yingxue.lesson.controller;

import com.yingxue.lesson.service.UserService;
import com.yingxue.lesson.vo.req.LoginReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UserController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/api")
@Api(tags = "用户模块",description = "用户模块相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/user/login")
    @ApiOperation(value = "用户登录接口")
    public Map<String,Object> login(@RequestBody LoginReqVO vo){
        Map<String,Object> result=new HashMap<>();
        result.put("code",0);
        result.put("data",userService.login(vo));
        return result;
    }
    @GetMapping("/user/{id}")
    @ApiOperation(value = "获取用户详情接口")
    @RequiresPermissions("sys:user:detail")
    public Map<String,Object> detail(@PathVariable("id") String id){
        Map<String,Object> result=new HashMap<>();
        result.put("code",0);
        result.put("data",userService.detail(id));
        return result;
    }
}
