package com.yingxue.lesson.hebing;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class hebing {

    class Request{
        String code;
        CompletableFuture<Map<String,Object>> future;
    }
    @PostConstruct
    public void init(){

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        //立刻执行，每隔10毫秒执行一次
        scheduledExecutorService.scheduleAtFixedRate(()->{
            int size = queue.size();
            if (size==0)return; //如果队列没有请求，就return，等待下一个10毫秒

            List<Request> list = new ArrayList<>();
            for (int i=0;i<size;i++){
                Request request = (Request) queue.poll();
                list.add(request);
            }
            System.out.println("合并了"+size+"次请求");

            //获得所抓取的请求中的code值，并根据这个进行查询并作为返回结果
            List<String> codes = new ArrayList<>();
            for (Request request : list) {
                codes.add(request.code);
            }


            //获取假的mock数据
            List<Map<String,Object>> response = dateMock(codes);


            //将结果一一对应返回
            Map<String,Map<String,Object>> resMap = new HashMap<>();
            for (Map<String, Object> map : response) {
                String code = map.get("code").toString();
                resMap.put(code,map);
            }


            //返回值给下面的方法 --》解决两个问题
            //1、下面方法在return前一定要保证定时任务执行完了
            //2、如何将值传递到下面方法中
            //使用callable  他会返回给外部线程一个Future<T>,这个泛型就是要的结果，这个Future.get()方法意义在于阻塞直到有返回值，如此可以保证问题1

            for (Request request : list) {
                Map<String,Object> map = resMap.get(request.code);
                request.future.complete(map);
            }


        },0,10, TimeUnit.MILLISECONDS);
    }


    LinkedBlockingQueue queue = new LinkedBlockingQueue();

    public Map<String,Object> query(String code) throws Exception{

        Request request = new Request();
        request.code = code;
        CompletableFuture<Map<String,Object>> future = new CompletableFuture();
        request.future = future;
        queue.add(request);
        return future.get();
    }



    static List<Map<String,Object>> dateMock(List<String> list){
        List<Map<String,Object>> res = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("Code1","aaaaa");
        map.put("Code2","bbbbb");
        res.add(map);
        return res;
    }
}
