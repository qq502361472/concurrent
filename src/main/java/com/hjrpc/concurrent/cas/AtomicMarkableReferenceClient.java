package com.hjrpc.concurrent.cas;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * ClassName: AtomicMarkableReferenceClient <br/>
 * Description: <br/>
 * date: 2019/6/3 15:17<br/>
 *
 * @author HuJian<br />
 * @since JDK 1.8
 */
public class AtomicMarkableReferenceClient {

    private static AtomicMarkableReference<Integer> markableReference = new AtomicMarkableReference<>(10,false);

    public static void main(String[] args) {

        boolean b = markableReference.compareAndSet(10, 5, false, true);

        boolean b1 = markableReference.compareAndSet(5, 5, true, true);

        System.out.println(b+"|||||"+b1);
    }
}
