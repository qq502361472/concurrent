package com.hjrpc.concurrent.exchange;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * ClassName: ExchangeClient <br/>
 * Description: <br/>
 * date: 2019/6/3 14:14<br/>
 *
 * @author HuJian<br   />
 * @since JDK 1.8
 */
public class ExchangeClient {

    public static void main(String[] args) {
        Exchanger<List<String>> exchanger = new Exchanger<>();

        new Thread(() -> {
            List<String> list1 = new ArrayList<>();
            list1.add("张三");
            list1.add("zhangsan");

            try {
                list1 = exchanger.exchange(list1);
                System.out.println("list1:"+list1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            List<String> list2 = new ArrayList<>();
            list2.add("李四");
            list2.add("lisi");
            try {
                list2 = exchanger.exchange(list2);
                System.out.println("list2:"+list2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
