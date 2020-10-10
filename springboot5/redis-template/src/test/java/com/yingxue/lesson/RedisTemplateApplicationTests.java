package com.yingxue.lesson;

import com.yingxue.lesson.entity.User;
import com.yingxue.lesson.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateApplicationTests {

    @Test
    public void contextLoads() {
    }

//    @Autowired
//    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private RedisService redisService;
    @Test
    public void testRedis(){
//        redisTemplate.opsForValue().get("num");
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        redisTemplate.opsForValue().set("num22","fsdf");
        System.out.println(redisService.hasKey("num22"));
//        User user=new User();
//        user.setPassowrd("666666");
//        user.setUsername("admin");
//        redisTemplate.opsForValue().set("user",user);

        User user=new User();
        user.setPassowrd("666666");
        user.setUsername("admin");
        redisService.set("user",user);
    }

}
