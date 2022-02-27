package com.example.commonIssues;

/*
    created by Atiye Mousavi
    Date: 2/15/2022
    Time: 4:33 PM
*/


public class SynchronizedVolatileCounter {
    private volatile int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public int getValue() {
        return counter;
    }
}
