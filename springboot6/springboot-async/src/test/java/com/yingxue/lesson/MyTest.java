package com.yingxue.lesson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MyTest {

    @org.junit.Test
    public void bianli(){
        List list = new ArrayList();
        list.add("bbb");
        list.add("ccc");
        list.add("aaaa");
        list.add(2,"ddd");
        Iterator it1 = list.iterator();
        while(it1.hasNext()){
            System.out.println(it1.next());
        }

        for (Iterator iterator = list.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!");
        for(Object tmp:list){
            System.out.println(tmp);
        }

        System.out.println("***************");
        for (int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }



    }
}
