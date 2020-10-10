package com.yingxue.lesson.web.controller;

import com.yingxue.lesson.vo.req.SwaggerReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: SwaggerController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/api")
@Api(tags = "测试swagger接口")
public class SwaggerController {
    @ApiOperation(value = "我的第一个swagger接口")
    @PostMapping("/swagger")
    public SwaggerReqVO testSwagger(@RequestBody SwaggerReqVO vo){
     return vo;
    }
}
