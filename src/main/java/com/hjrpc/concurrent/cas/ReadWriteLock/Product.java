package com.hjrpc.concurrent.cas.ReadWriteLock;

import lombok.Getter;

/**
 * ClassName: Product <br/>
 * Description: <br/>
 * date: 2019/6/3 15:48<br/>
 *
 * @author HuJian<br />
 * @since JDK 1.8
 */
public class Product {
    public Product(String name, double totalPrice, long count) {
        this.name = name;
        this.totalPrice = totalPrice;
        this.count = count;
    }

    private String name;

    @Getter
    private double totalPrice;

    @Getter
    private long count;


    public void setCount(int num){
        this.count -= num;
    }

}
