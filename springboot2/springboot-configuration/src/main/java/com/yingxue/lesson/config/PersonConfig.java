//package com.yingxue.lesson.config;
//
//import com.yingxue.lesson.entity.Person;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @ClassName: PersonConfig
// * TODO:类文件简单描述
// * @Author: 小霍
// * @UpdateUser: 小霍
// * @Version: 0.0.1
// */
//@Configuration
//public class PersonConfig {
//    @Value("${person.username}")
//    private String username;
//
//    @Value("${person.salary}")
//    private double salary;
//
//    @Value("${person.sex}")
//    private String sex;
//
//    @Value("${person.age}")
//    private int age;
//
//    @Bean        //把属性set到person，然后注入到容器，用@Bean注入到容器
//    public Person getPerson(){
//        Person person=new Person();
//        person.setSex(sex);
//        person.setAge(age);
//        person.setSalary(salary);
//        person.setUsername(username);
//        return person;
//    }
//}
