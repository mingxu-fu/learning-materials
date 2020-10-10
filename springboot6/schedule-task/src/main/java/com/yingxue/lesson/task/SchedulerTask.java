package com.yingxue.lesson.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: SchedulerTask
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Service
public class SchedulerTask {
    private static final SimpleDateFormat f=new SimpleDateFormat("HH:mm:ss");
//
//    @Scheduled(fixedRate = 5000)
//    public void processFixedRate(){
//        System.out.println("processFixedRate方式：开始定时任务，现在时间是："+f.format(new Date()));
//    }

//    @Scheduled(cron = "00 03 11 * * ?")
//    public void  processCron(){
//        System.out.println("processCron：开始定时任务，现在时间是："+f.format(new Date()));
//    }


}
