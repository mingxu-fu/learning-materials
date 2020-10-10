package com.yingxue.lesson.service.impl;

import com.yingxue.lesson.service.UploadService;
import com.yingxue.lesson.vo.UploadReqVO;
import com.yingxue.lesson.vo.UploadsReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName: UploadServiceimpl
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {
    @Value("${file.path}")
    private String FILE_PATH;
    @Override
    public String upload(UploadReqVO vo, Model model) {
        log.info("agentPhone={}",vo.getAgentPhone());
        log.info("agentName={}",vo.getAgentName());
        if(vo.getBusinessImg().isEmpty()){
            model.addAttribute("msg","上传失败的提示");
            return "success";
        }
        try {
            byte[] bytes = vo.getBusinessImg().getBytes();
            Path path= Paths.get(FILE_PATH+vo.getBusinessImg().getOriginalFilename());
            Files.write(path,bytes);
            model.addAttribute("msg","上传成功"+vo.getBusinessImg().getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    public String uploads(UploadsReqVO vo, Model model) {
        log.info("agentPhone={}",vo.getAgentPhone());
        log.info("agentName={}",vo.getAgentName());
        try {
//            冒号后为要遍历的集合，冒号前是以什么类型的对象接收
            for (MultipartFile file:vo.getBusinessImgs()) {
                if(file.isEmpty()){
                    model.addAttribute("msg","上传失败的提示");
                    return "success";
                }
                byte[] bytes = file.getBytes();
                Path path=Paths.get(FILE_PATH+file.getOriginalFilename());
                Files.write(path,bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("msg","多文件上传成功");
        return "success";
    }
}
