package com.example.streamReduce.entities;

/**
 * Create by Atiye Mousavi
 * Date: 12/13/2021
 * Time: 3:51 PM
 **/
public class User {
    private final String name;
    private final int age;
    private final Rating rating=new Rating();

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

    public Rating getRating() {
        return rating;
    }
    @Override
    public String toString() {
        return "User{" + "name=" + name + ", age=" + age + '}';
    }
}
