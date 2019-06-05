package com.hjrpc.concurrent.cas.ReadWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ClassName: UseReadWriteLock <br/>
 * Description: <br/>
 * date: 2019/6/3 16:00<br/>
 *
 * @author HuJian<br   />
 * @since JDK 1.8
 */
public class UseReadWriteLock implements ProductService {
    private Product product;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public UseReadWriteLock(Product product) {
        this.product = product;
    }

    @Override
    public Product getProduct() {
        try {
            readLock.lock();
            Thread.sleep(5);
            return this.product;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return null;
    }

    @Override
    public void setProduct(int num) {
        try {
            writeLock.lock();
            Thread.sleep(5);
            product.setCount(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }
}
