package com.yingxue.lesson.task;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class jihe {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("aaaa","111");
        map.put("bbb","222");
        map.put("ccc","333");
        map.put("ddd","444");
        map.put("eee","555");

        for (String mmm : map.keySet()){
            System.out.println(mmm+"------"+map.get(mmm));
        }

        for (Map.Entry<String, String> nnnn:map.entrySet()){
            System.out.println(nnnn+nnnn.getValue()+nnnn.getKey());
        }

        Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> it = iterator.next();
            System.out.println(it.getKey()+it.getValue());
        }





        List list = new ArrayList();
        list.add("bbb");
        list.add("ccc");
        list.add(2);
        list.add(2,"ddd");
        System.out.println("***************************************");
        for (Object ll :list){
            System.out.println(ll);
        }
        System.out.println("***************************************");
        Iterator iterator1 = list.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        t1.start();

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
