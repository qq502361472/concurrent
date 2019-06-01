package com.hjrpc.concurrent.ThreadStart;

import com.hjrpc.concurrent.ThreadStart.MyCallable;
import com.hjrpc.concurrent.ThreadStart.MyRunable;
import com.hjrpc.concurrent.ThreadStart.MyThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Concurrent {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        threadTest();
//        runableTest();
//        callableTest();
    }


    private static void threadTest() throws InterruptedException {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
//        MyThread t3 = new MyThread();
        t1.setDaemon(true);
        t2.setDaemon(true);
        t1.start();
        t2.start();
        Thread.sleep(2000L);
//        t3.start();

    }

    private static void runableTest(){
        MyRunable runable = new MyRunable();
        Thread t1 = new Thread(runable);
        Thread t2 = new Thread(runable);
        Thread t3 = new Thread(runable);
        t1.start();
        t2.start();
        t3.start();
        try {
            Thread.sleep(1000L);
            t1.interrupt();
            Thread.sleep(1000L);
            t2.interrupt();
            Thread.sleep(1000L);
            t3.interrupt();
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private  static void callableTest() throws ExecutionException, InterruptedException {
        MyCallable callable = new MyCallable();
        FutureTask<String> futureTask1 = new FutureTask<String>(callable);
        FutureTask<String> futureTask2 = new FutureTask<String>(callable);
        FutureTask<String> futureTask3 = new FutureTask<String>(callable);

        Thread t1 = new Thread(futureTask1);
        Thread t2 = new Thread(futureTask2);
        Thread t3 = new Thread(futureTask3);
        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(10*1000);
        System.out.println("main thread run 10 s");

        String s1 = futureTask1.get();
        System.out.println(s1);
        String s2 = futureTask2.get();
        System.out.println(s2);
        String s3 = futureTask3.get();
        System.out.println(s3);

    }
}
