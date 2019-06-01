package com.hjrpc.concurrent.waitnotify;

public class Express {
    private static int distance = 0;
    private static String place = "BeiJing";

    public Express(int distance,String place){
        Express.distance = distance;
        Express.place = place;
    }


    public synchronized void changeDistance(int distance){
        Express.distance = distance;
        System.out.println("changeDistance use notify");
        notify();
    }

    public synchronized void changePlace(String place){
        Express.place =place;
        System.out.println("changePlace use notifyAll");
        notifyAll();
    }


    public synchronized void watchDistance() throws InterruptedException {
        while (true){
            wait();
            System.out.println(Thread.currentThread().getName()+":distance change to "+distance);
        }
    }

    public synchronized void watchPlace() throws InterruptedException {
        while (true){
            wait();
            System.out.println(Thread.currentThread().getName()+":place change to "+place);
        }
    }
}
