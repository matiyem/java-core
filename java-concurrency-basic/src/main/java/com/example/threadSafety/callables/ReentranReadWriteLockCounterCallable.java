package com.example.threadSafety.callables;

import com.example.threadSafety.service.ReentrantReadWriteLockCounter;

import java.util.concurrent.Callable;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 1:13 PM
**/
public class ReentranReadWriteLockCounterCallable implements Callable<Integer> {

    private final ReentrantReadWriteLockCounter counter;

    public ReentranReadWriteLockCounterCallable(ReentrantReadWriteLockCounter counter) {
        this.counter = counter;
    }

    @Override
    public Integer call() throws Exception {
        counter.incrementCounter();
        return counter.getCounter();
    }
}
