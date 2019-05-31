package com.hjrpc.forkjoin;

import java.util.Random;


/**
 * @author 胡健
 */
public class RandomArrayUtil {

    public static void main(String[] args) {
        RandomArrayUtil.getRandomArray(4000);
    }

    public static Integer[] getRandomArray(Integer size){
        Integer[] randomNum = new Integer[size];

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            randomNum[i] = random.nextInt(size * 4);
//            System.out.println(randomNum[i]);
        }

        return randomNum;
    }
}
