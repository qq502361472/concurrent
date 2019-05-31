package com.hjrpc.forkjoin;

import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinClient {
    public static void main(String[] args) throws InterruptedException {

//        testRecursiveTask();

        File file = new File("F://");
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(file);
        ForkJoinPool pool = new ForkJoinPool();
        long l = System.currentTimeMillis();

        ForkJoinTask<Void> task = pool.submit(myRecursiveAction);


        System.out.println("task is running ");
        System.out.println("cost time1:" + (System.currentTimeMillis() - l));
//        pool.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println(pool.isQuiescent());
        System.out.println(pool.isTerminated());
//        while(!pool.isQuiescent()){
//
//        }
        Object o = new Object();
        System.out.println(task.join()+"cost time2:" + (System.currentTimeMillis() - l));
    }

    private static void testRecursiveTask() {
        Integer[] randomArray = RandomArrayUtil.getRandomArray(4000);
        ForkJoinPool pool = new ForkJoinPool();
        MyRecursiveTask task = new MyRecursiveTask(randomArray, 0, randomArray.length);
        long l = System.currentTimeMillis();
        //同步方法
//        Integer invoke = pool.invoke(task);
//        System.out.println(invoke);
        //异步方法
        pool.execute(task);
        System.out.println("task is running");
        System.out.println("cost time1:" + (System.currentTimeMillis() - l));
        System.out.println("get result:" + task.join() + ",cost time2:" + (System.currentTimeMillis() - l));
        pool.shutdown();
    }
}
