package com.example.mutex;

import com.google.common.util.concurrent.Monitor;

/*
    Create by Atiye Mousavi 
    Date: 1/29/2022
    Time: 3:57 PM
**/
public class SequenceGeneratorUsingMonitor extends SequenceGenerator {
//    تاکنون گزینه‌های پیاده‌سازی mutex با استفاده از ویژگی‌های ارائه شده توسط جاوا را دیده‌ایم.
//
//با این حال، کلاس Monitor کتابخانه Guava گوگل جایگزین بهتری برای کلاس ReentrantLock است. طبق مستندات آن، کدهایی که از مانیتور استفاده می‌کنند، خواناتر و کمتر از کدهایی که از ReentrantLock استفاده می‌کنند، دارای خطا هستند.

    private Monitor mutex = new Monitor();

    @Override
    public int getNextSequence() {
        mutex.enter();
        try {

            return super.getNextSequence();
        } finally {
            mutex.leave();
        }
    }
}
