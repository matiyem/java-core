package com.example.mutex;

import java.util.concurrent.Semaphore;

/*
    Create by Atiye Mousavi 
    Date: 1/29/2022
    Time: 4:30 PM
**/
public class SequenceGeneratorUsingSemaphore extends SequenceGenerator {
//    مانند ReentrantLock، کلاس Semaphore نیز در جاوا 1.5 معرفی شد.
//
//در حالی که در مورد mutex فقط یک رشته می تواند به یک بخش بحرانی دسترسی داشته باشد، Semaphore به تعداد ثابتی از نخ ها اجازه می دهد تا به بخش بحرانی دسترسی داشته باشند. بنابراین، می‌توانیم یک mutex را با تنظیم تعداد رشته‌های مجاز در یک Semaphore به یک پیاده‌سازی کنیم.
    private Semaphore mutex = new Semaphore(1);

    @Override
    public int getNextSequence() {
        try {
            mutex.acquire();
            return super.getNextSequence();
        } catch (InterruptedException e) {
            throw new RuntimeException("Exception in critical section.", e);
        }finally {
            mutex.release();
        }
    }
}
