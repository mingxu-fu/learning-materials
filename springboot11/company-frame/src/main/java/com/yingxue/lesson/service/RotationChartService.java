package com.yingxue.lesson.service;

import com.yingxue.lesson.entity.SysRotationChart;
import com.yingxue.lesson.vo.req.RotationChartDeleteReqVO;
import com.yingxue.lesson.vo.req.RotationChartReqAddVO;
import com.yingxue.lesson.vo.req.RotationChartUpdateReqVO;
import com.yingxue.lesson.vo.req.RotationReqVO;
import com.yingxue.lesson.vo.resp.PageVO;

import java.util.List;

/**
 * @ClassName: RotationChartService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface RotationChartService {

    PageVO<SysRotationChart> pageInfo(RotationReqVO vo);
    void addRotationChart(RotationChartReqAddVO vo, String userId);
    void updateRotationChart(String userId, RotationChartUpdateReqVO vo);
    int batchDeleteRotation(List<RotationChartDeleteReqVO> list);
    List<SysRotationChart> selectAll();
}
