package com.yingxue.lesson;

import com.yingxue.lesson.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisJedisApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RedisService redisService;

    @Test
    public void testRedis(){
        redisService.exists("name");
//        redisService.del("name","name1");
    }

}
