package com.hjrpc.concurrent.ReentrantLock;

import com.hjrpc.concurrent.ReentrantLock.ReentrantLockExpress;

public class ExpressClient {
    private static ReentrantLockExpress express = new ReentrantLockExpress(10, "BeiJing");

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    express.watchDistance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    express.watchPlace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(1000L);

        express.changeDistance(100);
        Thread.sleep(3000L);
//        express.changeDistance(200);
        System.out.println("--------------------------------------------");
//        express.changePlace("ShangHai");
        express.changePlace("ShenZhen");
    }
}
