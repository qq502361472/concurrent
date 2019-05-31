package com.hjrpc.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocakClient {
    private static ThreadLocal<Integer> tl = new ThreadLocal<Integer>();
    private static AtomicInteger ai = new AtomicInteger(0);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                tl.set(10);
                ai.set(10);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(tl.get());
                System.out.println(ai.get());
            }
        }).start();

    }

}
