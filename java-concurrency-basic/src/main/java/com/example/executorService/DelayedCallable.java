package com.example.executorService;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/*
    Create by Atiye Mousavi 
    Date: 1/17/2022
    Time: 12:28 PM
**/
public class DelayedCallable implements Callable<String> {

    private String name;
    private long period;
    private CountDownLatch latch;

    public DelayedCallable(String name, long period, CountDownLatch latch) {
        this(name, period);
        this.latch = latch;
    }

    public DelayedCallable(String name, long period) {
        this.name = name;
        this.period = period;
    }

    @Override
    public String call() {
        try {
            Thread.sleep(period);
            if (latch != null) {
                latch.countDown();
            }
        } catch (InterruptedException e) {
            //handle exception
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return name;
    }
}
