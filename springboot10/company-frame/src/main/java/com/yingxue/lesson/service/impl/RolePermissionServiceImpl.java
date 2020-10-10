package com.yingxue.lesson.service.impl;

import com.yingxue.lesson.entity.SysRolePermission;
import com.yingxue.lesson.exception.BusinessException;
import com.yingxue.lesson.exception.code.BaseResponseCode;
import com.yingxue.lesson.mapper.SysRolePermissionMapper;
import com.yingxue.lesson.service.RolePermissionService;
import com.yingxue.lesson.vo.req.RolePermissionOperationReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: RolePermissionServiceImpl
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;
    @Override
    public void addRolePermission(RolePermissionOperationReqVO vo) {
        sysRolePermissionMapper.removeByRoleId(vo.getRoleId());
        if(vo.getPermissionIds()==null||vo.getPermissionIds().isEmpty()){
            return;
        }
        List<SysRolePermission> list=new ArrayList<>();
        for (String permissionId:
             vo.getPermissionIds()) {
            SysRolePermission sysRolePermission=new SysRolePermission();
            sysRolePermission.setId(UUID.randomUUID().toString());
            sysRolePermission.setCreateTime(new Date());
            sysRolePermission.setRoleId(vo.getRoleId());
            sysRolePermission.setPermissionId(permissionId);
            list.add(sysRolePermission);
        }
        int i = sysRolePermissionMapper.batchInsertRolePermission(list);
        if(i==0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    public List<String> getRoleIdsByPermissionId(String permissionId) {
        return sysRolePermissionMapper.getRoleIdsByPermissionId(permissionId);
    }

    @Override
    public int removeRoleByPermissionId(String permissionId) {
        return sysRolePermissionMapper.removeByPermissionId(permissionId);
    }

    @Override
    public List<String> getPermissionIdsByRoleId(String roleId) {
        return sysRolePermissionMapper.getPermissionIdsByRoleId(roleId);
    }

    @Override
    public int removeByRoleId(String roleId) {
        return sysRolePermissionMapper.removeByRoleId(roleId);
    }
    @Override
    public List<String> getPermissionIdsByRoles(List<String> roleIds) {

        return sysRolePermissionMapper.getPermissionIdsByRoles(roleIds);
    }

}
