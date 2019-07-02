package com.hjrpc.concurrent.productandconsumer;

public class ProAndConClient {
    public static void main(String[] args) {
//        ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();
//
//        new Thread(producerAndConsumer.producer).start();
//        new Thread(producerAndConsumer.consumer).start();


        Queue4ProducerAndConsumer queue4ProducerAndConsumer = new Queue4ProducerAndConsumer();
        new Thread(queue4ProducerAndConsumer.producer).start();
        new Thread(queue4ProducerAndConsumer.consumer).start();
    }
}
