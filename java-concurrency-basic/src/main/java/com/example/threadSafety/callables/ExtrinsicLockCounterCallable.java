package com.example.threadSafety.callables;

import com.example.threadSafety.service.ObjectLockCounter;

import java.util.concurrent.Callable;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 1:06 PM
**/
public class ExtrinsicLockCounterCallable implements Callable<Integer> {

    private final ObjectLockCounter counter;

    public ExtrinsicLockCounterCallable(ObjectLockCounter counter) {
        this.counter = counter;
    }

    @Override
    public Integer call() throws Exception {
        counter.incrementCounter();
        return counter.getCounter();
    }
}
