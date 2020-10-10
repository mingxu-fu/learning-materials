package com.yingxue.lesson.mapper;

import com.yingxue.lesson.entity.SysUser;
import org.springframework.stereotype.Repository;


public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}