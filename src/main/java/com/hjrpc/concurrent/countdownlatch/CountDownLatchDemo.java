package com.hjrpc.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static CountDownLatch count = new CountDownLatch(7);

    public static void main(String[] args) {
        long l = System.currentTimeMillis();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "1-1:" + count);
                count.countDown();
                System.out.println(Thread.currentThread().getName() + "1-2:" + count);
                count.countDown();
            }
        }).start();

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "2:" + count);
                    count.countDown();
                }
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "3:" + count);
                    count.countDown();
                }
            }).start();
        }

        long k = System.currentTimeMillis() ;
        count.countDown();
        System.out.println("main thread is await:" + (k-l));
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread is done:"+(System.currentTimeMillis()-k));
    }
}
