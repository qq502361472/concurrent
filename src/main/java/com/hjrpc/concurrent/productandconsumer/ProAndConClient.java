package com.hjrpc.concurrent.productandconsumer;

public class ProAndConClient {
    public static void main(String[] args) {
        ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();

//        for (int i = 0; i < 3; i++) {
            new Thread(producerAndConsumer.producer).start();
            new Thread(producerAndConsumer.consumer).start();
//        }
    }
}
