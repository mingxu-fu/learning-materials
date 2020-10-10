package com.yingxue.lesson.service;

import com.yingxue.lesson.vo.UploadReqVO;

import com.yingxue.lesson.vo.UploadsReqVO;
import org.springframework.ui.Model;

/**
 * @ClassName: UploadService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface UploadService {
    String upload(UploadReqVO vo, Model model);
    String uploads(UploadsReqVO vo,Model model);

}
