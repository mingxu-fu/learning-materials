package com.yingxue.lesson.mapper;

import com.yingxue.lesson.entity.SysRolePermission;

import java.util.List;

public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);

    int batchInsertRolePermission(List<SysRolePermission> list);

    List<String> getRoleIdsByPermissionId(String permissionId);

    //根据permissionId 删除角色和菜单权限关联表相关数据
    int removeByPermissionId(String permissionId);

    //根据角色id获取该角色关联的菜单权限id集合
    List<String> getPermissionIdsByRoleId(String roleId);

    List<String> getPermissionIdsByRoles(List<String> roleIds);


    //根绝角色id删除角色和菜单权限关联表相关数据
    int removeByRoleId(String roleId);

}