package com.yingxue.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: RolePermissionOperationReqVO
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Data
public class RolePermissionOperationReqVO {

    @ApiModelProperty(value = "角色id")
    private String roleId;
    @ApiModelProperty(value = "菜单权限集合")
    private List<String> permissionIds;
}
