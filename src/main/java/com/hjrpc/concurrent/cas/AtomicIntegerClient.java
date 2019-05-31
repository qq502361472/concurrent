package com.hjrpc.concurrent.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerClient {

    private static Integer intnum = new Integer(0);
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static CountDownLatch countDownLatch = new CountDownLatch(50);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Integer原值："+intnum+++",更新后的值:"+intnum);
                    System.out.println("AtomicInteger原值："+atomicInteger.getAndIncrement()+",更新后的值:"+atomicInteger.get());
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("intnum:"+intnum);
        System.out.println("atomicInteger"+atomicInteger);
    }
}
