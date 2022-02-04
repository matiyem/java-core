package com.example.concurrent.countDownLatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/*
    Create by Atiye Mousavi 
    Date: 1/30/2022
    Time: 3:50 PM
**/
public class Worker implements Runnable{
    //        اساساً، با استفاده از CountDownLatch می‌توانیم تا زمانی که رشته‌های دیگر وظیفه‌ای را انجام دهند، یک رشته مسدود شود.
//به عبارت ساده، یک CountDownLatch دارای یک فیلد شمارنده است که می‌توانید آن را طبق نیاز ما کاهش دهید. سپس می‌توانیم از آن برای مسدود کردن یک رشته تماس استفاده کنیم تا زمانی که تا صفر شمارش معکوس شود.
//
//اگر پردازش موازی انجام می‌دادیم، می‌توانیم CountDownLatch را با همان مقدار برای شمارنده به‌عنوان تعداد رشته‌هایی که می‌خواهیم روی آنها کار کنیم، نمونه‌سازی کنیم. سپس، می‌توانیم پس از اتمام هر رشته، countdown() را فراخوانی کنیم، و تضمین کنیم که رشته وابسته که فراخوانی await() را می‌دهد تا زمانی که thread‌های کارگر به پایان برسد، مسدود می‌شود.
    private final List<String> outputScraper;
    private final CountDownLatch countDownLatch;

    public Worker(List<String> outputScraper, CountDownLatch countDownLatch) {
        this.outputScraper = outputScraper;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("Doing some logic");
        outputScraper.add("Counted down");
        countDownLatch.countDown();
    }
}
