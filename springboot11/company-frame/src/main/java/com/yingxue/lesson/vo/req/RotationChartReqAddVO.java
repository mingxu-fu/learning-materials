package com.yingxue.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: RotationChartReqAddVO
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Data
public class RotationChartReqAddVO {

    @ApiModelProperty(value = "广告地址")
    @NotBlank(message = "广告地址不能为空")
    private String url;

    @ApiModelProperty(value = "轮播图名称")
    @NotBlank(message = "轮播图名称不能为空")
    private String name;

    @ApiModelProperty(value = "轮播图地址")
    @NotBlank(message = "轮播图地址不能为空")
    private String fileUrl;

    @ApiModelProperty(value = "轮播图排序位置")
    @NotNull(message = "轮播图排序位置不能为空")
    private Integer sort;
    @ApiModelProperty(value = "轮播图描述")
    private String description;
}
