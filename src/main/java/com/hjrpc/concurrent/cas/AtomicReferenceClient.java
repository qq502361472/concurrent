package com.hjrpc.concurrent.cas;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceClient {

    private static AtomicReference<User> referenceUser = new AtomicReference<>();
    private static User user = new User("张三", 18);

    public static void main(String[] args) {

        for (int i = 0; i < 50; i++) {
            new Thread(()-> {
                    referenceUser.set(user);
                    User user2 = new User("李四", 15);
                    User user3 = new User("王五", 10);
                    referenceUser.compareAndSet(user, user2);
                    System.out.println(referenceUser.get());
                    boolean b = referenceUser.compareAndSet(user2, user3);
                    System.out.println(b);
                    System.out.println(referenceUser.get());
            }).start();
        }
    }
}
