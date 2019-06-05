package com.hjrpc.concurrent.cas.ReadWriteLock;

/**
 * ClassName: ReadThread <br/>
 * Description: <br/>
 * date: 2019/6/3 16:10<br/>
 *
 * @author HuJian<br   />
 * @since JDK 1.8
 */
public class ReadThread implements Runnable {
    private ProductService productService;

    public ReadThread(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run() {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Product product = productService.getProduct();
        }
        System.out.println("read耗费时间："+(System.currentTimeMillis()-l)+"ms");
    }
}
