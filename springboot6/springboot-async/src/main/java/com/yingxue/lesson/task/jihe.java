package com.yingxue.lesson.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class jihe {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("aaaa","111");
        map.put("bbb","222");
        map.put("ccc","333");
        map.put("ddd","444");
        map.put("eee","555");
        for (String map2:map.keySet()){
            System.out.println(map2);
        }

        for (Map.Entry<String, String> map3:map.entrySet()){
            System.out.println(map3.getKey()+"*****"+map3.getValue());
        }


        List list = new ArrayList();
        list.add("aaa");
        list.add(1);
        String[] str = new String[2];

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
