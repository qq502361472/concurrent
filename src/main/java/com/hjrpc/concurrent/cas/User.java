package com.hjrpc.concurrent.cas;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
