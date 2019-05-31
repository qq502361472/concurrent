package com.hjrpc.forkjoin;

import java.util.concurrent.RecursiveTask;

public class MyRecursiveTask extends RecursiveTask<Integer> {

    private static final int YU_ZHI = 100;
    private Integer[] intArray ;
    private Integer startIndex ;
    private Integer endIndex;

    public MyRecursiveTask(Integer[] intArray,Integer startIndex,Integer endIndex){
        this.intArray = intArray;
        this.endIndex = endIndex;
        this.startIndex = startIndex;

    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if(endIndex-startIndex<YU_ZHI){
            for (int i = startIndex; i < endIndex; i++) {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sum += intArray[i];
            }
            return sum;
        }

        int mid = (endIndex + startIndex) / 2;
        RecursiveTask<Integer> task1 = new MyRecursiveTask(intArray, startIndex, mid);
        RecursiveTask<Integer> task2 = new MyRecursiveTask(intArray, mid, endIndex);
        //同步方法
//        invokeAll(task1,task2);
        //异步方法
        task1.fork();
        task2.fork();
        return task1.join()+task2.join();
    }

}
