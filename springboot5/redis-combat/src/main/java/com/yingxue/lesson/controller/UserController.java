package com.yingxue.lesson.controller;

import com.yingxue.lesson.entity.SysUser;
import com.yingxue.lesson.service.UserService;
import com.yingxue.lesson.vo.req.LoginReqVO;
import com.yingxue.lesson.vo.req.RegisterReqVO;
import com.yingxue.lesson.vo.resp.LoginRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public LoginRespVO login(@RequestBody LoginReqVO vo){
        return userService.login(vo);
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "获取用户信息接口")
    public SysUser getUserInfo(@PathVariable("id") String id){
        return userService.getUserInfo(id);
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "用户注册接口")
    public String register(@RequestBody RegisterReqVO vo){
        return userService.register(vo);
    }
    @GetMapping("/user/code/{phone}")
    public String getCode(@PathVariable("phone") String phone){
        return userService.getCode(phone);
    }
}
