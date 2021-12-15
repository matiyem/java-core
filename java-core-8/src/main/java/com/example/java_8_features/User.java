package com.example.java_8_features;

import java.util.Optional;

/**
 * Create by Atiye Mousavi
 * Date: 12/13/2021
 * Time: 1:11 PM
 **/
public class User {
    private String name;
    private Address address;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public static boolean isRealUser(User user) {
        return true;
    }

    public String getOrThrow() {
        String value = null;
//        یک گزینه optional را برمی‌گرداند که حاوی یک مقدار غیر تهی است.
        Optional<String> valueOpt = Optional.ofNullable(value);
        String result = valueOpt.orElseThrow(CustomException::new).toUpperCase();
        return result;
    }

    public boolean isLegalName(String name) {
        return name.length() > 3 && name.length() < 16;
    }
}
