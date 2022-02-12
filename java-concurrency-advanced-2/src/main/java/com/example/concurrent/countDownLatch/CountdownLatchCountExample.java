package com.example.concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;

/*
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 10:11 AM
 **/


public class CountdownLatchCountExample {
    private int count;

    public CountdownLatchCountExample(int count) {
        this.count = count;
    }

    public boolean callTwiceInSameThread() {
        CountDownLatch countDownLatch = new CountDownLatch(count);
        Thread t = new Thread(() -> {
            countDownLatch.countDown();
            countDownLatch.countDown();
        });
        t.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return countDownLatch.getCount() == 0;
    }

    public static void main(String[] args) {
        CountdownLatchCountExample ex = new CountdownLatchCountExample(2);
        System.out.println("Is CountDown completed : " + ex.callTwiceInSameThread());
    }
}
