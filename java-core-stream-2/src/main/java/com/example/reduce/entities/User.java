package com.example.reduce.entities;

/**
 * created by Atiye Mousavi
 * Date: 12/24/2021
 * Time: 6:32 PM
 **/


public class User {
    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return "User{" + "name=" + name + ", age=" + age + '}';
    }
}
