package com.hjrpc.concurrent.productandconsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Queue4ProducerAndConsumer {
    private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
    public Producer producer = new Producer();
    public Consumer consumer = new Consumer();
    public Random random = new Random();

    public Queue4ProducerAndConsumer(){
    }

    class Producer implements Runnable{
        @Override
        public void run() {
            for (;;){
                try {
                    int i = random.nextInt(100);
                    queue.put(i);
                    System.out.println("producer produce num:"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable{
        @Override
        public void run() {
            for (;;) {
                try {
                    Integer take = queue.take();
                    System.out.println("consumer consume num:" + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
