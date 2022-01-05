package com.example.debug.entity;

/**
 * Create by Atiye Mousavi
 * Date: 1/3/2022
 * Time: 2:57 PM
 **/
public class Customer {

    private final String name;
    private final int age;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
