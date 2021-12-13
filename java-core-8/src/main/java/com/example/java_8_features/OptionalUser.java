package com.example.java_8_features;

import java.util.Optional;

/**
 * Create by Atiye Mousavi
 * Date: 12/13/2021
 * Time: 1:09 PM
 **/
public class OptionalUser {

    private OptionalAddress address;

    public Optional<OptionalAddress> getAddress() {
        return Optional.of(address);
    }

    public void setAddress(OptionalAddress address) {
        this.address = address;
    }
}
