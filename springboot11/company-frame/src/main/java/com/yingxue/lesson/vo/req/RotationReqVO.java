package com.yingxue.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: RotationReqVO
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Data
public class RotationReqVO {

    @ApiModelProperty(value = "分页页数")
    private Integer pageNum=1;

    @ApiModelProperty(value = "当前页条数")
    private Integer pageSize=10;
}
