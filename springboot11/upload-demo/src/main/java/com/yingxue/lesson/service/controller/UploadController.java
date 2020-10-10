package com.yingxue.lesson.service.controller;

import com.yingxue.lesson.service.UploadService;
import com.yingxue.lesson.vo.UploadReqVO;

import com.yingxue.lesson.vo.UploadsReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName: UploadController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Controller
public class UploadController {

    @Autowired
    private UploadService uploadService;
    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @GetMapping("/uploads")
    public String uploads(){
        return "uploads";
    }

    @PostMapping("/upload")
    public String uploadFile(UploadReqVO vo, Model model){
        return uploadService.upload(vo,model);
    }


    @PostMapping("/uploads")
    public String uploadFiles(UploadsReqVO vo,Model model){
        return uploadService.uploads(vo,model);
    }

}
