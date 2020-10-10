package com.yingxue.lesson.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: UploadReqVO
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Data
public class UploadsReqVO {
    private String agentName;
    private String agentPhone;
    private MultipartFile[] businessImgs;
}
