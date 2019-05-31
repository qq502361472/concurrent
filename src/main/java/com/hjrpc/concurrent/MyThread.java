package com.hjrpc.concurrent;

public class MyThread extends Thread {
    @Override
    public void run() {
//        while (!isInterrupted()) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName()+"do finally work!");
            }
//        }
    }
}
