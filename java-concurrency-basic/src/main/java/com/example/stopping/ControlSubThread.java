package com.example.stopping;

import java.util.concurrent.atomic.AtomicBoolean;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 11:13 AM
**/
public class ControlSubThread implements Runnable {
//    در این مقاله کوتاه، توقف یک Thread در جاوا را پوشش خواهیم داد – که به این سادگی نیست زیرا متد Thread.stop () منسوخ شده است.
//
//همانطور که در این به روز رسانی از Oracle توضیح داده شد، stop() می تواند منجر به خراب شدن اشیاء نظارت شده شود.

//    بیایید با کلاسی شروع کنیم که یک موضوع ایجاد و راه اندازی می کند. این کار به خودی خود به پایان نمی رسد، بنابراین ما به راهی برای متوقف کردن آن موضوع نیاز داریم.
//
//ما از یک پرچم اتمی برای آن استفاده خواهیم کرد:
//    به جای داشتن یک حلقه while برای ارزیابی یک true ثابت، از یک AtomicBoolean استفاده می‌کنیم و اکنون می‌توانیم اجرا را با تنظیم درست/نادرست شروع/توقف کنیم.
//
//همانطور که در مقدمه ما برای متغیرهای اتمی توضیح داده شد، استفاده از یک AtomicBoolean از تداخل در تنظیم و بررسی متغیر از رشته های مختلف جلوگیری می کند.

    //    چه اتفاقی می‌افتد وقتی sleep() روی یک بازه زمانی طولانی تنظیم شود، یا اگر منتظر قفلی باشیم که ممکن است هرگز آزاد نشود؟
//
//ما با خطر مسدود شدن برای مدت طولانی روبرو هستیم یا هرگز به طور کامل خاتمه نمی دهیم.
//
//ما می توانیم interrupt() را برای این موقعیت ها ایجاد کنیم، بیایید چند متد و یک پرچم جدید به کلاس اضافه کنیم:
    private Thread worker;
    private int interval = 100;
    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicBoolean stopped = new AtomicBoolean(true);

    public ControlSubThread(int sleepInterval) {
        interval = sleepInterval;
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void stop() {
        running.set(false);
    }

    public void interrupt() {
        running.set(false);
        worker.interrupt();
    }

    public boolean isRunning() {
        return running.get();
    }

    public boolean isStopped() {
        return stopped.get();
    }


    @Override
    public void run() {
//        ما یک متد interrupt() اضافه کرده‌ایم که پرچم در حال اجرا را روی false تنظیم می‌کند و متد interrupt() thread کارگر را فراخوانی می‌کند.
//
//اگر thread هنگام فراخوانی در حالت Sleep باشد، sleep() با یک InterruptedException خارج می شود، مانند هر تماس مسدود کننده دیگری.
//
//این موضوع را به حلقه برمی‌گرداند و از آنجایی که اجرا نادرست است، از آن خارج می‌شود.
        running.set(true);
        stopped.set(false);
        while (running.get()) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, Failed to complete operation");
            }
        }
        stopped.set(true);
    }

}
