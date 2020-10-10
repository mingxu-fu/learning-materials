package com.yingxue.lesson.service;

import com.yingxue.lesson.vo.req.RolePermissionOperationReqVO;

import java.util.List;


/**
 * @ClassName: RolePermissionService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface RolePermissionService {
    void addRolePermission(RolePermissionOperationReqVO vo);
    List<String> getRoleIdsByPermissionId(String permissionId);
    int removeRoleByPermissionId(String permissionId);
    List<String> getPermissionIdsByRoleId(String roleId);
    int removeByRoleId(String roleId);
    List<String> getPermissionIdsByRoleIds(List<String> roleIds);

}
