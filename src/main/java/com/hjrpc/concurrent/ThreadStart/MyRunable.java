package com.hjrpc.concurrent.ThreadStart;

public class MyRunable implements Runnable {
    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        while (!thread.isInterrupted()) {
            System.out.println(thread.getName());
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                thread.interrupt();
                e.printStackTrace();
            }
        }
    }
}
