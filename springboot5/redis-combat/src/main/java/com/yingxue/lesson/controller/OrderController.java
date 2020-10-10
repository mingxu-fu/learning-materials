package com.yingxue.lesson.controller;

import com.yingxue.lesson.service.CodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: OrderController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/api")
@Api(tags = "订单模块",description = "订单模块相关接口")
public class OrderController {

    @Autowired
    private CodeService codeService;
    @GetMapping("/order/code/{type}")
    @ApiOperation(value = "生产订单编码")
    public String getOrderCode(@PathVariable("type") String type){
        return codeService.getOrderCode(type);
    }
}
