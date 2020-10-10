package com.yingxue.lesson;

import com.yingxue.lesson.task.AsyncTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAsyncApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private AsyncTask asyncTask;

    @Test
    public void testTask() throws Exception{
//        asyncTask.doTaskOne();
//        asyncTask.doTaskTwo();
//        asyncTask.doTaskThree();
        long start = System.currentTimeMillis();
        Future<String> taskOne=asyncTask.doTaskOne();
        Future<String> taskTwo=asyncTask.doTaskTwo();
        Future<String> taskThree=asyncTask.doTaskThree();

        while (true){
            if(taskOne.isDone()&&taskTwo.isDone()&&taskThree.isDone()){
                break;
            }
//            判断三个任务是否都结束了，没结束就等1秒后再判断
            Thread.sleep(10000);
        }

        long end = System.currentTimeMillis();
        System.out.println("总计耗时=================="+(start-end)+"ms");

    }





}
