package com.hjrpc.concurrent.cas.ReadWriteLock;

import java.util.Random;

/**
 * ClassName: WriteThread <br/>
 * Description: <br/>
 * date: 2019/6/3 16:12<br/>
 *
 * @author HuJian<br   />
 * @since JDK 1.8
 */
public class WriteThread implements Runnable {
    private ProductService productService;

    public WriteThread(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run() {

        long l = System.currentTimeMillis();
        Random random = new Random();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            productService.setProduct(random.nextInt(100));
        }
        System.out.println("write耗费时间："+(System.currentTimeMillis()-l)+"ms");
    }
}
