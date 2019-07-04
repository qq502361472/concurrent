package com.hjrpc.concurrent.MyThreadPool;

public class PoolClient {

    public static void main(String[] args) {
        HjrpcThreadPool pool = new HjrpcThreadPool();

        for (int i = 0; i < 50; i++) {
            pool.execute(()->{
                System.out.println(Thread.currentThread().getName()+":execute task,queuesize:"+pool.getQueueSize());
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        System.out.println(pool.getQueueSize());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        System.out.println(pool.getQueueSize());
    }
}
