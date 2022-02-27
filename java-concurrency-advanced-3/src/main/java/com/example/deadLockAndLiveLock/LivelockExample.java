package com.example.deadLockAndLiveLock;

/*
    created by Atiye Mousavi
    Date: 2/15/2022
    Time: 4:47 PM
*/


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LivelockExample {
//    اکنون، برای نشان دادن شرایط قفل زنده، همان مثال بن بست را که قبلاً در مورد آن صحبت کردیم، استفاده می کنیم. در این مثال همچنین، thread T1 عملیات1 را فراخوانی می کند و thread T2 عملیات2 را فرا می خواند. با این حال، ما منطق این عملیات را کمی تغییر خواهیم داد.
//
//هر دو نخ برای تکمیل کار خود به دو قفل نیاز دارند. هر نخ قفل اول خود را می گیرد اما متوجه می شود که قفل دوم در دسترس نیست. بنابراین، برای اینکه اجازه دهیم نخ دیگر ابتدا کامل شود، هر نخ اولین قفل خود را آزاد می کند و سعی می کند دوباره هر دو قفل را بدست آورد.
//همانطور که در لاگ ها می بینیم، هر دو رشته به طور مکرر قفل می گیرند و آزاد می کنند. به همین دلیل، هیچ یک از رشته ها قادر به تکمیل عملیات نیستند.
//    برای جلوگیری از ایجاد قفل زنده، باید شرایطی را که باعث ایجاد قفل زنده می شود بررسی کنیم و سپس راه حلی متناسب با آن پیدا کنیم.
//
//به عنوان مثال، اگر ما دو رشته داشته باشیم که به طور مکرر قفل‌ها را بدست می‌آورند و آزاد می‌کنند و منجر به ایجاد حالت زنده می‌شود، می‌توانیم کد را طوری طراحی کنیم که نخ‌ها در بازه‌های زمانی تصادفی دوباره سعی کنند قفل‌ها را بدست آورند. این به نخ ها فرصت مناسبی برای بدست آوردن قفل های مورد نیازشان می دهد.
//
//راه دیگری برای رسیدگی به مشکل زنده بودن در مثال سیستم پیام رسانی که قبلاً در مورد آن صحبت کردیم، قرار دادن پیام های ناموفق در یک صف جداگانه برای پردازش بیشتر به جای بازگرداندن دوباره آنها در همان صف است.
    private Lock lock1=new ReentrantLock(true);
    private Lock lock2=new ReentrantLock(true);

    public static void main(String[] args) {
        LivelockExample livelock=new LivelockExample();
        new Thread(livelock::operation1,"T1").start();
        new Thread(livelock::operation2,"T2").start();

    }
    public void operation1(){
        while (true){
            tryLock(lock1,50);
            print("lock1 acquired,trying to acquire lock2.");
            sleep(50);
            if (tryLock(lock2)){
                print("lock2 acquired.");
            }else {
                print("cannot acquire lock2,releasing lock1.");
                lock1.unlock();
                continue;
            }
            print("executing first operation");
            break;
        }
        lock2.unlock();
        lock1.unlock();
    }
    public void operation2(){
        while (true){
            tryLock(lock2,50);
            print("lock2 acquired, trying to acquire lock1.");
            sleep(50);

            if (tryLock(lock1)){
                print("lock1 acquire.");
            }else {
                print("cannot acquired lock1, releasing lock2.");
                lock2.unlock();
                continue;
            }
            print("executing second operation");
            break;
        }
    }
    public void print(String message){
        System.out.println("Thread " + Thread.currentThread().getName() + ": " + message);
    }
    public void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void tryLock(Lock lock,long millis){
        try {
            lock.tryLock(millis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean tryLock(Lock lock){
        return lock.tryLock();
    }
}
