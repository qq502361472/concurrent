package com.hjrpc.concurrent.cas.ReadWriteLock;

/**
 * ClassName: ProductService <br/>
 * Description: <br/>
 * date: 2019/6/3 15:53<br/>
 *
 * @author HuJian<br />
 * @since JDK 1.8
 */
public interface ProductService {

    public Product getProduct();

    public void setProduct(int num);
}
