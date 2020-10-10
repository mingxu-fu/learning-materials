package com.yingxue.lesson.task;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("bbb");
        list.add("ccc");
        list.add("aaaa");
        list.add(2,"ddd");

        String name = (String) list.get(2);
        System.out.println(name);
        System.out.println(list);


        Collection collection = list;
        System.out.println("@@@@@@@@@@"+collection);

        List list2 = new ArrayList(collection);
        list2.add("eee");
        System.out.println("$$$$$"+list2);


        list2.addAll(collection);
        System.out.println("^^^^^^"+list2);


        List list3 = new ArrayList();
        list3.add("bbb");
        list2.removeAll(list3);
        System.out.println("*****"+list2);

//        list2.removeAll(collection);
//        System.out.println("&&&&&&&"+list2);
        List lists = new ArrayList();
        lists.get(10);
    }


public void test3() throws ExecutionException, InterruptedException {
        Callable callable = new Callable()  {
            @Override
            public Object call() throws Exception {
                Object info = (new HashMap<>()).toString();
                return info;
            }
        };

        FutureTask<Object> mytast = new FutureTask<>(callable);
        new Thread(mytast).start();

        Object aaa = mytast.get();

    LinkedBlockingQueue waiter = new LinkedBlockingQueue();


    }


}
