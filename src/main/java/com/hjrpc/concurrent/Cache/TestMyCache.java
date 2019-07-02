package com.hjrpc.concurrent.Cache;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestMyCache {

    public static void main(String[] args) {

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
