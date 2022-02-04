package com.example.concurrent.cyclicBarrier;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
    Create by Atiye Mousavi 
    Date: 1/30/2022
    Time: 3:52 PM
**/
public class CyclicBarrierCompletionMethodExample {
    private int count;
    private int threadCount;
    private final AtomicInteger updateCount;

    public CyclicBarrierCompletionMethodExample(int count, int threadCount) {
        this.count = count;
        this.threadCount = threadCount;
        this.updateCount = new AtomicInteger(0);
    }

    public int countTrips() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count, () -> {
            updateCount.incrementAndGet();
        });
        ExecutorService es = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            es.execute(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        es.shutdown();
        try {
            es.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return updateCount.get();
    }

    public static void main(String[] args) {
        CyclicBarrierCompletionMethodExample ex = new CyclicBarrierCompletionMethodExample(5, 20);
        System.out.println("Count : " + ex.countTrips());
    }
}
