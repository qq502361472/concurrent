package com.hjrpc.concurrent.optimize.utils;

public class SimulateBuinessUtil {

    public static void buinessMilliseconds(long mill){
        try {
            Thread.sleep(mill);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
