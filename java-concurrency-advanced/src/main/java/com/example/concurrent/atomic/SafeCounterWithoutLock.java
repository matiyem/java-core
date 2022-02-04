package com.example.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/*
    Create by Atiye Mousavi 
    Date: 1/30/2022
    Time: 3:38 PM
**/
public class SafeCounterWithoutLock {
    private final AtomicInteger counter = new AtomicInteger(0);

    int getValue() {
        return counter.get();
    }

    void increment() {
        while (true) {
            int existingValue = getValue();
            int newValue = existingValue + 1;
            if (counter.compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }
}
