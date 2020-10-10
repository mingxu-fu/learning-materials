package com.yingxue.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: RotationChartDeleteReqVO
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Data
public class RotationChartDeleteReqVO {

    @ApiModelProperty(value = "轮播图id")
    @NotBlank(message = "轮播图id不能为空")
    private String id;
    @ApiModelProperty(value = "轮播图图片地址")
    @NotBlank(message = "轮播图图片地址不能为空")
    private String fileUrl;
}
