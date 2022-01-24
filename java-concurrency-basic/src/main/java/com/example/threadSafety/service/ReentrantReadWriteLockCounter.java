package com.example.threadSafety.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 12:36 PM
**/
public class ReentrantReadWriteLockCounter {
//    یکی دیگر از مکانیسم های قدرتمندی که می توانیم برای دستیابی به Thread-safety استفاده کنیم، استفاده از پیاده سازی ReadWriteLock است.
//
//قفل ReadWriteLock در واقع از یک جفت قفل مرتبط استفاده می کند، یکی برای عملیات فقط خواندنی و دیگری برای عملیات نوشتن.
//
//در نتیجه، تا زمانی که هیچ موضوعی برای نوشتن یک منبع وجود نداشته باشد، ممکن است موضوعات زیادی برای خواندن یک منبع وجود داشته باشد. علاوه بر این، نوشتن موضوع در منبع از خواندن آن توسط سایر موضوعات جلوگیری می کند.
//

    private int counter;
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public ReentrantReadWriteLockCounter() {
        this.counter = 0;
    }

    public void incrementCounter() {
        writeLock.lock();

        try {
            counter += 1;
        } finally {
            writeLock.unlock();
        }
    }

    public int getCounter() {
        readLock.lock();
        try {
            return counter;
        }finally {
            readLock.unlock();
        }
    }
}
