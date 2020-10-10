package com.yingxue.lesson.mapper;

import com.yingxue.lesson.entity.SysLog;
import com.yingxue.lesson.vo.req.SysLogPageReqVO;

import java.util.List;


public interface SysLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    List<SysLog> selectAll(SysLogPageReqVO vo);


    int batchDeletedLog(List<String> logIds);


}