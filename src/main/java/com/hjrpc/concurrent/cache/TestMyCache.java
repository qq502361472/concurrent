package com.hjrpc.concurrent.cache;

public class TestMyCache {

    public static void main(String[] args) {

        int i1 = Runtime.getRuntime().availableProcessors();
        System.out.println("当前机器CPU核心数量："+i1);

        Cache<String, String> cache = new Cache<>();

        cache.put("name","张三",3000L);
        cache.put("name","李四1",4000L);
        cache.put("name1","李四2",1000L);
        cache.put("name2","李四3",2000L);
        cache.put("name3","李四4",5000L);

        for (int i = 0; i < 12; i++) {
            System.out.println(i*500);
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
