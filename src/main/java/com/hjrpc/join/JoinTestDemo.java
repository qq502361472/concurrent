package com.hjrpc.join;

import jdk.nashorn.internal.objects.annotations.Setter;

public class JoinTestDemo implements Runnable {
    private Thread lastThread ;

    public  JoinTestDemo(Thread thread){
        this.lastThread = thread;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();

        System.out.println("lastThread:"+lastThread.getName()+" will be join "+thread.getName());
        try {
            lastThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getName()+" is done");
    }
}
