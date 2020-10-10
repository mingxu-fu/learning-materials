package com.yingxue.lesson.shiro;


import java.util.*;

public class demo {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("aaaa","1111");
        map.put("bbbb","2222");
        map.put("ccccc","3333");
        for (String key:map.keySet()){
            System.out.println(key+map.get(key));
        }

        for (Map.Entry<String,String> map2:map.entrySet()){
            System.out.println(map2.getKey()+map2.getValue());
        }

        List<String> list = new ArrayList<>();
        list.add("hhhhh");
        for (String a:list){
            System.out.println(a);
        }

        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
