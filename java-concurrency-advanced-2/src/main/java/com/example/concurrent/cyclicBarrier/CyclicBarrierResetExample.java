package com.example.concurrent.cyclicBarrier;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 11:36 AM
 **/


public class CyclicBarrierResetExample {
    private int count;
    private int threadCount;
    private final AtomicInteger updateCount;

    public CyclicBarrierResetExample(int count, int threadCount) {
        this.count = count;
        this.threadCount = threadCount;
        this.updateCount = new AtomicInteger(0);
    }

    public int countWaits() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);

        ExecutorService es = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            es.execute(() -> {
                try {
                    if (cyclicBarrier.getNumberWaiting() > 0) {
                        updateCount.incrementAndGet();
                    }
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
        CyclicBarrierResetExample ex = new CyclicBarrierResetExample(7, 20);
    }
}
