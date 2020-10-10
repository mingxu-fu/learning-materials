package com.yingxue.lesson.service.impl;

import com.yingxue.lesson.contants.Contant;
import com.yingxue.lesson.service.CodeService;
import com.yingxue.lesson.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * @ClassName: CodeServiceImpl
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    private RedisService redisService;
    @Override
    public String getOrderCode(String type) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        String date=simpleDateFormat.format(System.currentTimeMillis());
        Long number=redisService.incrby(Contant.ORDER_CODE_KEY+date,1);
        String pad=padRight(number.toString(),7,"0");
        return date+type+pad;
    }

    private String padRight(String oldStr,int len,String alexin){
        String str="";
        int strlen=oldStr.length();
        for(int i=0;i<len-strlen;i++){
            str=str+alexin;
        }

        str=str+oldStr;
        return str;
    }
}
