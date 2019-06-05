package com.hjrpc.concurrent.bitoperation;

public class BitOperationClient {
    public static void main(String[] args) {
        System.out.println("5:0" + Integer.toBinaryString(5));
        System.out.println("8:" + Integer.toBinaryString(8));

        System.out.println("5&8:" + (5 & 8) + ",二进制:" + Integer.toBinaryString(5 & 8));
        System.out.println("5|8:" + (5 | 8) + ",二进制:" + Integer.toBinaryString(5 | 8));
        System.out.println("5^8:" + (5 ^ 8) + ",二进制:" + Integer.toBinaryString(5 ^ 8));
        System.out.println("~8:" + (~8) + ",二进制:" + Integer.toBinaryString(~8));
    }
}
