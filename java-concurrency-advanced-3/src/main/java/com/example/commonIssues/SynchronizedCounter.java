package com.example.commonIssues;

/*
    created by Atiye Mousavi
    Date: 2/15/2022
    Time: 4:31 PM
*/


public class SynchronizedCounter {
    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }
    public synchronized int getValue(){
        return counter;
    }
}
