package com.hjrpc.concurrent.cas.ReadWriteLock;

/**
 * ClassName: ReadWriteLockClient <br/>
 * Description: <br/>
 * date: 2019/6/3 16:07<br/>
 *
 * @author HuJian<br />
 * @since JDK 1.8
 */
public class ReadWriteLockClient {
    public static void main(String[] args) {
        Product product = new Product("手机",100000,10000);
        ProductService productService = new UseSynchronized(product);
//        ProductService productService = new UseReadWriteLock(product);
        for (int i = 0; i < 3; i++) {
            new Thread(new WriteThread(productService)).start();
        }
        for (int i = 0; i < 30; i++) {
            new Thread(new ReadThread(productService)).start();
        }
    }
}
