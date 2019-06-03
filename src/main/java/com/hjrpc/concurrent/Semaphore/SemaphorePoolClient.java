package com.hjrpc.concurrent.Semaphore;

import com.hjrpc.concurrent.Pool.HjrpcPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphorePoolClient {
    private static final int threadCount = 50;
    private static final int times = 20;

    private static AtomicInteger gotCount = new AtomicInteger(0);
    private static AtomicInteger notgotCount = new AtomicInteger(0);

    public static void main(String[] args) throws SQLException {

        final SemaphorePool semaphorePool = new SemaphorePool(10);

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < times; j++) {
                        Connection connection = semaphorePool.getConnection(100L);
                        if (connection == null) {
                            notgotCount.incrementAndGet();
                        } else {
                            gotCount.incrementAndGet();
                            try {
                                connection.createStatement();
                                connection.commit();
                                semaphorePool.relaseConnection(connection);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("get connection total times:"+threadCount*times);
        System.out.println("got connection times:"+gotCount.intValue());
        System.out.println("not go connection times:"+notgotCount.intValue());
    }
}
