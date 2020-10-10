package com.yingxue.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: RotationChartUpdateReqVO
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Data
public class RotationChartUpdateReqVO {

    @ApiModelProperty(value = "轮播图信息id")
    @NotBlank(message = "轮播图信息id不能为空")
    private String id;

    @ApiModelProperty(value = "广告地址")
    private String url;

    @ApiModelProperty(value = "轮播图名称")
    private String name;

    @ApiModelProperty(value = "轮播图地址")
    private String fileUrl;

    @ApiModelProperty(value = "轮播图排序位置")
    private Integer sort;
    @ApiModelProperty(value = "轮播图描述")
    private String description;
}
