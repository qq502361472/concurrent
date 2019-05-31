package com.hjrpc.concurrent.lock;

public class VolatileDemo implements Runnable{
    private  int a = 1;
    private  int b = 2;
    @Override
    public void run() {
        boolean b = Thread.currentThread().getName().endsWith("0");
        if(b){
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            write();
        }else{
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            read();
        }

    }

    public void write() {
        a=3;
        b=a;
    }

    public synchronized void  read() {
        System.out.println(Thread.currentThread().getName()+"[read]:a="+a+",b="+b);
    }

}
