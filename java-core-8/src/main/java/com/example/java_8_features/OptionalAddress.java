package com.example.java_8_features;

import lombok.Data;

import java.util.Optional;

/**
 * Create by Atiye Mousavi
 * Date: 12/13/2021
 * Time: 1:06 PM
 **/
public class OptionalAddress {
    private String street;

    public Optional<String> getStreet() {
        return Optional.ofNullable(street);
    }

    public void setStreet(String street) {
        street = street;
    }
}
