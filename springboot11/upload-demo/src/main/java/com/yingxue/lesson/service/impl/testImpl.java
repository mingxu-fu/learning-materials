package com.yingxue.lesson.service.impl;

import com.yingxue.lesson.service.UploadService;
import com.yingxue.lesson.vo.UploadReqVO;
import com.yingxue.lesson.vo.UploadsReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class testImpl implements UploadService {
    @Value("${file.path}")
    private String File_Path;

    @Override
    public String upload(UploadReqVO vo, Model model) {
        log.info("agentname",vo.getAgentName());
        log.info("agentPhone",vo.getAgentPhone());
        if (vo.getBusinessImg().isEmpty()){
            model.addAttribute("msg","文件上传失败");
            return "success";
        }
        try {
            byte[] bytes = vo.getBusinessImg().getBytes();
            Path path = Paths.get(File_Path+vo.getBusinessImg().getOriginalFilename());
            Files.write(path,bytes);
            model.addAttribute("msg","上传成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @Override
    public String uploads(UploadsReqVO vo, Model model) {
        log.info("agentName",vo.getAgentName());
        log.info("agentPhone",vo.getAgentPhone());

        for (MultipartFile file:vo.getBusinessImgs()){
            if (file.isEmpty()){
                model.addAttribute("msg","上传失败");
                return "success";
            }

            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(File_Path+file.getOriginalFilename());
                Files.write(path,bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("msg","多文件上传成功");
        return "success";
    }
}
