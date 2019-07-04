package com.hjrpc.concurrent.aqs;

public class TestMyLock {
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        MyLock lock = new MyLock();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    lock.lock();
                    for (int j = 0; j < 10; j++) {
                        try {
                            lock.lock();
                            count = count + 1;
                        } finally {
                            lock.unlock();
                        }
                        try {
                            Thread.sleep(5L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    lock.unlock();
                }

            }).start();
        }
        Thread.sleep(1000L);
        System.out.println(count);
    }
}
