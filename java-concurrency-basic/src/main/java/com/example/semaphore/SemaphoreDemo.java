package com.example.semaphore;

import java.util.concurrent.Semaphore;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 10:56 AM
**/
public class SemaphoreDemo {
//    Semaphore برای مسدود کردن دسترسی سطح رشته به بخشی از منبع فیزیکی یا منطقی استفاده می شود. سمافور حاوی مجموعه ای از مجوزها است. هر زمان که یک رشته سعی می کند وارد بخش بحرانی شود، باید سمافور را بررسی کند که آیا مجوز در دسترس است یا نه.
//
//اگر مجوز در دسترس نباشد (از طریق tryAcquire())، موضوع اجازه ندارد به بخش بحرانی بپرد. با این حال، اگر مجوز در دسترس باشد، دسترسی داده می شود و شمارنده مجوز کاهش می یابد.
//
//هنگامی که thread در حال اجرا بخش بحرانی را آزاد می کند، مجدداً شمارنده مجوز افزایش می یابد (انجام شده توسط روش release()).
//
//می‌توانیم با استفاده از روش tryAcquire (تایم‌اوت طولانی، واحد TimeUnit) یک بازه زمانی برای دستیابی به دسترسی تعیین کنیم.
//
//همچنین می‌توانیم تعداد مجوزهای موجود یا تعداد رشته‌هایی را که منتظر دریافت سمافور هستند، بررسی کنیم.
//
//قطعه کد زیر را می توان برای پیاده سازی سمافور استفاده کرد:

    static Semaphore semaphore = new Semaphore(10);

    public void execute() {
        System.out.println("Available permit : " + semaphore.availablePermits());
        System.out.println("Number of threads waiting to acquire: " + semaphore.getQueueLength());

        if (semaphore.tryAcquire()) {
            try {
                //perform some critical operations
            }finally {
                semaphore.release();
            }
        }
    }
}
