package com.example.threadSafety.service;

import java.util.concurrent.locks.ReentrantLock;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 12:31 PM
**/
public class ReentrantLockCounter {
//    جاوا مجموعه ای بهبود یافته از پیاده سازی های Lock را ارائه می دهد که رفتار آنها کمی پیچیده تر از قفل های ذاتی است که در بالا مورد بحث قرار گرفت.
//
//با قفل‌های ذاتی، مدل کسب قفل نسبتاً سفت و سخت است: یک رشته قفل را می‌گیرد، سپس یک متد یا بلوک کد را اجرا می‌کند و در نهایت قفل را آزاد می‌کند، بنابراین رشته‌های دیگر می‌توانند آن را بدست آورند و به روش دسترسی پیدا کنند.
//
//هیچ مکانیزمی وجود ندارد که رشته های در صف را بررسی کند و به طولانی ترین رشته های انتظار دسترسی اولویت دهد.
//
//نمونه‌های ReentrantLock به ما این امکان را می‌دهند که دقیقاً همین کار را انجام دهیم، بنابراین از ابتلای رشته‌های در صف به برخی از انواع گرسنگی منابع جلوگیری می‌کنیم:

    private int counter;
    private final ReentrantLock reLock = new ReentrantLock(true);

    public ReentrantLockCounter() {
        this.counter = 0;
    }

    public void incrementCounter() {
//        سازنده ReentrantLock یک پارامتر بولی منصفانه اختیاری می گیرد. هنگامی که روی true تنظیم می شود و چندین رشته در حال تلاش برای به دست آوردن یک قفل هستند، JVM به طولانی ترین رشته انتظار اولویت می دهد و به قفل دسترسی می دهد.
//        جای تعجب نیست که Lock ابزاری برای مسدود کردن سایر رشته‌ها از دسترسی به بخش خاصی از کد است، به غیر از رشته‌ای که در حال حاضر آن را اجرا می‌کند.
//
//تفاوت اصلی بین یک بلوک قفل و یک بلوک همگام‌سازی شده این است که بلوک همگام‌سازی شده به طور کامل در یک متد وجود دارد. با این حال، ما می توانیم عملیات lock() و unlock() Lock API را در متدهای جداگانه داشته باشیم.
        reLock.lock();
        try {
            counter += 1;
        } finally {
            reLock.unlock();
        }
    }

    public int getCounter() {
        return counter;
    }
}
