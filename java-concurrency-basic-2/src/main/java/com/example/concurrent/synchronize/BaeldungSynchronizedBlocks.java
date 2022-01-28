package com.example.concurrent.synchronize;

/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 4:21 PM
**/
public class BaeldungSynchronizedBlocks {

    private int count = 0;
    private static int staticCount = 0;

    public void performSynchronisedTask() {
//        گاهی اوقات ما نمی خواهیم کل روش را همگام سازی کنیم، فقط برخی دستورالعمل ها را در درون آن قرار دهیم. ما می‌توانیم با اعمال همگام‌سازی شده در یک بلوک به این هدف دست پیدا کنیم:
//        توجه داشته باشید که این پارامتر را به بلوک همگام‌سازی شده ارسال کردیم. این شیء مانیتور است. کد داخل بلوک روی شی مانیتور همگام می شود. به عبارت ساده، تنها یک رشته در هر شی مانیتور می تواند در داخل آن بلوک کد اجرا شود.
        synchronized (this) {
            setCount(getCount() + 1);
        }
    }

    public static void performStaticSyncTask() {
//        اگر متد ثابت بود، نام کلاس را به جای مرجع شی ارسال می‌کردیم و کلاس یک مانیتور برای همگام‌سازی بلوک خواهد بود:
        synchronized (BaeldungSynchronizedBlocks.class) {
            setStaticCount(getStaticCount() + 1);
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static int getStaticCount() {
        return staticCount;
    }

    public static void setStaticCount(int staticCount) {
        BaeldungSynchronizedBlocks.staticCount = staticCount;
    }
}
