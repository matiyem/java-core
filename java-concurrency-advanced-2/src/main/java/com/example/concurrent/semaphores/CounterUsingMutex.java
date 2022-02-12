package com.example.concurrent.semaphores;

import java.util.concurrent.Semaphore;

/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 3:44 PM
 **/


public class CounterUsingMutex {
    private final Semaphore mutex;
    private int count;

    public CounterUsingMutex() {
        this.mutex = new Semaphore(1);
        this.count = 0;
    }
    void increase() throws InterruptedException {
        mutex.acquire();
        this.count=this.count + 1 ;
        Thread.sleep(1000);
        mutex.release();
    }

    public int getCount() {
        return count;
    }
    boolean hasQueuedThreads(){
        return mutex.hasQueuedThreads();
    }
}
