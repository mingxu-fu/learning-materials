package com.yingxue.lesson.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @ClassName: AsyncTask
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Component
public class AsyncTask {
    public static Random random=new Random();
    @Async("myTaskExecutor")
    public Future<String> doTaskOne()throws Exception{
        System.out.println("开始任务一");
        long startTime=System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long endTime=System.currentTimeMillis();

        System.out.println("完成任务一消耗的时间："+(endTime-startTime)+"毫秒");
        return new AsyncResult<>("任务一完成");
    }
    @Async("myTaskExecutor")
    public Future<String> doTaskTwo()throws Exception{
        System.out.println("开始任务二");
        long startTime=System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long endTime=System.currentTimeMillis();
        System.out.println("完成任务二消耗的时间："+(endTime-startTime)+"毫秒");
        return new AsyncResult<>("任务二完成");
    }
    @Async("myTaskExecutor")
    public Future<String> doTaskThree()throws Exception{
        System.out.println("开始任务三");
        long startTime=System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long endTime=System.currentTimeMillis();
        System.out.println("完成任务三消耗的时间："+(endTime-startTime)+"毫秒");
        return new AsyncResult<>("任务三完成");
    }

    @Async("myTaskExecutor")
    public Future<String> doTaskFour() throws Exception {
        System.out.println("开始任务四");
        long startTime = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long endTime = System.currentTimeMillis();
        System.out.println("完成任务四消耗的时间："+(endTime-startTime)+"秒");
        return new AsyncResult<>("完成任务四");
    }

    @Async("myTaskExecutor")
    public Future<String> doTaskFive()throws Exception{
        System.out.println("开始任务五");
        long startTime = System.currentTimeMillis();
        Thread.sleep(10000);
        long endTime = System.currentTimeMillis();
        System.out.println("任务五耗时："+(endTime-startTime)+"秒");
        return new AsyncResult<>("完成任务五");
    }


    public void main(){
        synchronized (this){

        }
    }

}
