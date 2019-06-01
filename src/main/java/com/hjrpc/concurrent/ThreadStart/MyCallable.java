package com.hjrpc.concurrent.ThreadStart;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread currentThread = Thread.currentThread();
        Thread.sleep(5*1000);
        System.out.println(currentThread.getName()+" run 5000 s");
        return currentThread.getName()+":returnValue";
    }
}
