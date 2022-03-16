package com.example.threadsStartSameTime;

import java.time.Instant;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
    Create by Atiye Mousavi 
    Date: 3/15/2022
    Time: 12:31 PM
**/
public class WorkerWithCyclicBarrier extends Thread {
    private CyclicBarrier barrier;

    public WorkerWithCyclicBarrier(String name, CyclicBarrier barrier) {
        this.barrier = barrier;
        this.setName(name);
    }

    @Override
    public void run() {
        try {
            System.out.printf("[ %s ] created, blocked by the barrier\n", getName());
            barrier.await();
            System.out.printf("[ %s ] starts at: %s\n", getName(), Instant.now());

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
