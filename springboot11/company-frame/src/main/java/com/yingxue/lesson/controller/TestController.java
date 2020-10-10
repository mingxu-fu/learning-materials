package com.yingxue.lesson.controller;

import com.yingxue.lesson.exception.BusinessException;
import com.yingxue.lesson.exception.code.BaseResponseCode;
import com.yingxue.lesson.utils.DataResult;
import com.yingxue.lesson.vo.req.TestReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/test")
@Api(tags = "讲课测试相关模块")
public class TestController {

    @GetMapping("/index")
    @ApiOperation(value = "Swagger 测试 index 接口")
    public String testResult(){
        return "Hello World";
    }

    @GetMapping("/home")
    @ApiOperation(value = "测试统一返回格式接口")
    public DataResult getHome(){
//        DataResult result=DataResult.success();
        DataResult result=DataResult.getResult(BaseResponseCode.SUCCESS);
//        result.setData("哈哈哈我请求成功了");
            List<String> list=new ArrayList<>();
            list.add("a");
            list.add("b");
            result.setData(list);
            int i=1/0;

        return result;
    }
    @GetMapping("/business/error")
    @ApiOperation(value = "测试主动抛出业务异常接口")
    public DataResult<String> testBusinessError(@RequestParam String type){
        if(!type.equals("1")||type.equals("2")||type.equals("3")){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        DataResult<String> result=new DataResult(0,type);
        return result;
    }

    @PostMapping("/valid/error")
    @ApiOperation(value = "测试Validator抛出业务异常接口")
    public DataResult testValid(@RequestBody @Valid TestReqVO vo){
        DataResult result=DataResult.success();
        return result;
    }
}
