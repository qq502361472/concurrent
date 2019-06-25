package com.hjrpc.concurrent.productandconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerAndConsumer {
    private final int MAX_LEN = 10;
    private Queue<Integer> queue = new LinkedList<Integer>();
    private Random random = new Random();
    public Producer producer = new Producer();
    public Consumer consumer = new Consumer();

    class Producer extends Thread {
        @Override
        public void run() {
            for (; ; ) {
                synchronized (queue) {
                    while (queue.size() == MAX_LEN) {
                        queue.notify();
                        System.out.println("生产者容器已经满了，等待消费者来消费..................");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    int i = random.nextInt(100);
                    queue.add(i);
                    System.out.println("生产者生产商品：" + i + ",待处理商品数量：" + queue.size());
                    queue.notify();
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            for (; ; ) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        queue.notify();
                        System.out.println("队列容器已经空了，等待生产者来生产..................");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Integer poll = queue.poll();
                    queue.notify();
                    System.out.println("消费者消费商品：" + poll + ",待处理商品数量：" + queue.size());
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
