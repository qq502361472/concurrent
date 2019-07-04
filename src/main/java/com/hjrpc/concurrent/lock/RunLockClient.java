package com.hjrpc.concurrent.lock;

public class RunLockClient {
    public static void main(String[] args) {

        MyRunLockTest myRunLockTest = new MyRunLockTest();
        for (int i = 0; i < 2; i++) {
            Thread t1 = new Thread(myRunLockTest);
            t1.setName("Thread-"+i+":");
            t1.start();
        }
//        VolatileDemo volatileDemo = new VolatileDemo();
//        while(true){
//            Thread t1 = new Thread(volatileDemo);
//            t1.start();
//        }
    }
}
