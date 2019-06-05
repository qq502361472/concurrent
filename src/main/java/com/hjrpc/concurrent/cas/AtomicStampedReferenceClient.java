package com.hjrpc.concurrent.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ClassName: AtomicStampedReferenceClient <br/>
 * Description: <br/>
 * date: 2019/6/3 15:25<br/>
 *
 * @author HuJian<br />
 * @since JDK 1.8
 */
public class AtomicStampedReferenceClient {

    private static AtomicStampedReference<String> stampedReference = new AtomicStampedReference<>("张三",0);

    public static void main(String[] args) {
        boolean b = stampedReference.compareAndSet("张三", "李四", 0, 1);
        boolean b2 = stampedReference.compareAndSet("张三", "李四", 0, 1);

        System.out.println(b+"|||"+b2);
    }
}
