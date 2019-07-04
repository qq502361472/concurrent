package com.hjrpc.concurrent.MyThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public class HjrpcThreadPool implements Executor {

    private static int THREAD_COUNT = 5;

    private static int QUEUE_COUNT = 100;

    private Worker[] threads;

    private BlockingQueue<Runnable> queue;

    public HjrpcThreadPool() {
        this(THREAD_COUNT, QUEUE_COUNT);
    }

    public HjrpcThreadPool(int threadCount, int queueCount) {
        this.THREAD_COUNT = threadCount;
        this.QUEUE_COUNT = queueCount;

        threads = new Worker[threadCount];
        queue =new LinkedBlockingQueue<Runnable>(queueCount);
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Worker();
            threads[i].start();
        }
    }

    public int getQueueSize(){
        return queue.size();
    }

    public void shutdown(){
        for (Worker thread : threads) {
            thread.interrupt();
            thread=null;
        }
        queue.clear();
    }

    @Override
    public void execute(Runnable command) {
        try {
            queue.put(command);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class Worker extends Thread {

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    Runnable runnable = queue.take();
                    if (runnable == null)
                        continue;
                    runnable.run();
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
