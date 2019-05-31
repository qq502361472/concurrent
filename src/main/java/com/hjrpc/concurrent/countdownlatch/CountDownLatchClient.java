package com.hjrpc.concurrent.countdownlatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * ClassName: CountDownLatchClient <br/>
 * Description: <br/>
 * date: 2019/5/31 15:43<br/>
 *
 * @author HuJian<br   />
 * @since JDK 1.8
 */
public class CountDownLatchClient {

//    private static CountDownLatch countDownLatch = new CountDownLatch(6);

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":开始执行任务");
                    Random random = new Random();
                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":第一段任务执行完毕");
                    long l = System.currentTimeMillis();
//                    countDownLatch.countDown();

                    System.out.println("当前时间:" + new SimpleDateFormat("hh:mm:ss.SSS").format(new Date()));
                    System.out.println(Thread.currentThread().getName()
                            + ":开始执行第二段任务，中间等待" + (System.currentTimeMillis() - l) + "ms");
                }
            }).start();
        }


        new Thread(new Runnable() {
            public void run() {
                System.out.println("业务线程执行");
                System.out.println(Thread.currentThread().getName() + "业务线程");
                long l = System.currentTimeMillis();
//                countDownLatch.countDown();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("当前时间:" + new SimpleDateFormat("hh:mm:ss.SSS").format(new Date()));
                System.out.println(Thread.currentThread().getName()
                        + ":等待" + (System.currentTimeMillis() - l) + "ms");
            }
        }).start();

        System.out.println("main线程执行完毕");

//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
