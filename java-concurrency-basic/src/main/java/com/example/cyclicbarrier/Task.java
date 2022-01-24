package com.example.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
    Create by Atiye Mousavi 
    Date: 1/17/2022
    Time: 11:28 AM
**/
public class Task implements Runnable {
//    CyclicBarrier تقریباً مانند CountDownLatch کار می کند با این تفاوت که می توانیم دوباره از آن استفاده کنیم. برخلاف CountDownLatch، به چندین رشته اجازه می‌دهد تا قبل از فراخوانی وظیفه نهایی با استفاده از متد await() (معروف به شرط مانع) برای یکدیگر منتظر بمانند.
//
//برای شروع شرط مانع باید یک نمونه کار Runnable ایجاد کنیم:
    private CyclicBarrier barrier;

    public Task(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread :" + Thread.currentThread().getName() + " is waiting");
            barrier.await();
            System.out.println("Thread: " + Thread.currentThread().getName() + " is released");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
