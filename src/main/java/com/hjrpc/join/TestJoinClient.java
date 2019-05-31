package com.hjrpc.join;

public class TestJoinClient {

    public static void main(String[] args) {
        Thread main = Thread.currentThread();
        System.out.println(main.getName());

        for (int i = 0; i < 10; i++) {
            JoinTestDemo testDemo = new JoinTestDemo(main);
            Thread thread = new Thread(testDemo);
            thread.start();
            main = thread;
        }

        System.out.println(Thread.currentThread().getName());
    }
}
