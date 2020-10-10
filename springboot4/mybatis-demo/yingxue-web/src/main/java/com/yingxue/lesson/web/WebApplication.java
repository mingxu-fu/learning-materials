package com.yingxue.lesson.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
/**
 * @ClassName: WebApplication
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@SpringBootApplication(scanBasePackages = {"com.yingxue.lesson"})
@ComponentScan(value = "com.lesson.fmx")
//@ComponentScans(value = {@ComponentScan(value = "com.yingxue.lesson"),@ComponentScan(value = "com.lesson.fmx")})
@MapperScan("com.yingxue.lesson.mapper")
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}



