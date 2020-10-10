package com.yingxue.lesson.mapper;

import com.yingxue.lesson.entity.SysRole;
import com.yingxue.lesson.vo.req.RolePageReqVO;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectAll(RolePageReqVO vo);

    List<SysRole> getRoleInfoByIds(List<String> ids);
}