package com.hjrpc.concurrent.cas.ReadWriteLock;

/**
 * ClassName: UseSynchronized <br/>
 * Description: <br/>
 * date: 2019/6/3 15:55<br/>
 *
 * @author HuJian<br />
 * @since JDK 1.8
 */
public class UseSynchronized implements ProductService {

    private Product product;

    public UseSynchronized(Product product) {
        this.product = product;
    }

    @Override
    public synchronized Product getProduct() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.product;
    }

    @Override
    public synchronized void setProduct(int num) {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        product.setCount(num);
    }
}
