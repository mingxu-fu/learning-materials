package com.yingxue.lesson.task;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class demo {

    private static demo instance = null;

    private demo(){

    }

    public static demo getInstance() {

        if (instance == null) {
            instance = new demo();
        }
        return instance;
    }

    public static void main(String[] args) {


        Map<String,String> map= new HashMap();
        List list = new ArrayList();
        list.add("aaaaa");
        list.add("bbbb");
        list.add("cccccc");
        map.put("a","qqqq");
        map.put("b","wwww");
        map.put("c","eeeee");
        System.out.println(map);

        for (Object d:map.entrySet()){
            System.out.println(d);
        }

        System.out.println("#######");


        for (String a :map.keySet()){
            System.out.println(a+map.get(a));
        }

        for (Map.Entry<String,String> map2:map.entrySet()){
            System.out.println(map2.getKey()+map2.getValue());
        }

        int num = 2;
        Integer integer = new Integer(num);


    }


    public String fangfa(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("aaaa");
            }
        });

        t1.start();
        return "aaaa";
    }

    public void fangfa2(){
       Callable callable = new Callable() {
           @Override
           public Object call() throws Exception {
               return null;
           }
       };

        FutureTask task = new FutureTask(callable);
        new Thread(task).start();
    }


}
