package com.hjrpc.concurrent.testJar;


import JobFrame.ProgressTaskPool;

import java.util.Random;

public class TestClient {
    public static void main(String[] args) {
        Random random = new Random();
        ProgressTaskPool<Integer, String> pool = ProgressTaskPool.getInstance();
        TestTask testTask = new TestTask();
        String task = "task";
        for (int i = 0; i < 5; i++) {
            pool.registerJob(task+i,testTask,1000,5*1000L);
            for (int j = 0; j < 1000; j++) {
                pool.pushTask(task+i,random.nextInt(130));
            }
        }

        Thread thread = new Thread(() -> {
            while (true) {
                String progress0 = ProgressTaskPool.getProgress(task+0);
                String progress1 = ProgressTaskPool.getProgress(task+1);
                String progress2 = ProgressTaskPool.getProgress(task+2);
                String progress3 = ProgressTaskPool.getProgress(task+3);
                String progress4 = ProgressTaskPool.getProgress(task+4);
                System.out.println(progress0);
                System.out.println(progress1);
                System.out.println(progress2);
                System.out.println(progress3);
                System.out.println(progress4);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


}
