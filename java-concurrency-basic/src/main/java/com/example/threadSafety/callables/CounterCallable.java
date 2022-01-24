package com.example.threadSafety.callables;

import com.example.threadSafety.service.Counter;

import java.util.concurrent.Callable;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 1:04 PM
**/
public class CounterCallable implements Callable<Integer> {

    private final Counter counter;

    public CounterCallable(Counter counter) {
        this.counter = counter;
    }

    @Override
    public Integer call() throws Exception {
        counter.incrementCounter();
        return counter.getCounter();
    }
}
