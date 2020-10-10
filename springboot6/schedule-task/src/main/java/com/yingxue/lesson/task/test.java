package com.yingxue.lesson.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class test {

    private static final SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(fixedRate = 6000)
//    public void test(){
//        System.out.println("测试一现在时间是："+f.format(new Date()));
//    }
//
//    @Scheduled(cron = "0/6 * * * * ?")
//    public void test2(){
//        System.out.println("测试二的时间是："+f.format(new Date()));
//    }
//
//
//    @Scheduled(cron = "00 33 15 * * ?")
//    public void test3(){
//        System.out.println("测试三开始");
//    }


    @Scheduled(fixedRate = 3000)
    public void test4(){
        System.out.println("测试四时间"+(f.format(new Date())));
    }

    @Scheduled(cron = "0/2 * * * * ?")
    public void test5(){
        System.out.println("测试五"+f.format(new Date()));
    }
}
