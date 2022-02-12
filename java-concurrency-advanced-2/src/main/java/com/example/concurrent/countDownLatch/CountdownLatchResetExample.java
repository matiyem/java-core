package com.example.concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 10:24 AM
 **/


public class CountdownLatchResetExample {
    private int count;
    private int threadCount;
    private final AtomicInteger updateCount;

    public CountdownLatchResetExample(int count, int threadCount) {
        this.count = count;
        this.threadCount = threadCount;
        this.updateCount = new AtomicInteger(0);
    }

    public int countWaits() {
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ExecutorService es = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            es.execute(() -> {
                long preValue = countDownLatch.getCount();
                countDownLatch.countDown();
                if (countDownLatch.getCount() != preValue) {
                    updateCount.incrementAndGet();
                }
            });
        }
        es.shutdown();
        return updateCount.get();
    }

    public static void main(String[] args) {
        CountdownLatchResetExample ex = new CountdownLatchResetExample(5, 20);
        System.out.println("Count : " + ex.countWaits());
    }
}
