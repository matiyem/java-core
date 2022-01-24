package com.example.threadSafety.service;

import java.util.concurrent.atomic.AtomicInteger;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 12:13 PM
**/
public class AtomicCounter {
//    thread-safe است
    private final AtomicInteger counter=new AtomicInteger();

    public AtomicCounter(){}

    public void incrementCounter(){
        counter.incrementAndGet();
    }
    public synchronized int getCounter(){
        return counter.get();
    }
}
