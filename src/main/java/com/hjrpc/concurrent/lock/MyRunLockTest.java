package com.hjrpc.concurrent.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class MyRunLockTest implements Runnable {
    private static  AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        while (count.intValue() < 50) {
            if (count.intValue() % 2 == 0) {
                try {
                    obLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    classLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private synchronized void obLock() throws InterruptedException {
        Thread.sleep(200L);
        System.out.println(Thread.currentThread().getName() + "this is object lock" + count.getAndIncrement());
    }

    private synchronized static void classLock() throws InterruptedException {
        Thread.sleep(200L);
        System.out.println(Thread.currentThread().getName() + "this is class lock" + count.getAndIncrement());
    }
}
