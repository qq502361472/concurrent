package com.hjrpc.concurrent.Semaphore;

import java.util.Random;

/**
 * ClassName: SemaphoreClient <br/>
 * Description: <br/>
 * date: 2019/5/31 17:05<br/>
 *
 * @author HuJian<br />
 * @since JDK 1.8
 */
public class SemaphoreClient {

    public static void main(String[] args) {


        for (int i = 0; i <50 ; i++) {
            try {
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(new CheckIn()).start();
        }

    }

}
