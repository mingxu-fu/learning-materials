package com.yingxue.lesson.mapper;

import com.yingxue.lesson.entity.SysUser;
import com.yingxue.lesson.vo.req.UserPageReqVO;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

   SysUser getUserInfoByName(String username);

   List<SysUser> selectAll(UserPageReqVO vo);

}