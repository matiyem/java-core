package com.example.concurrent.sleepWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 12:46 PM
**/
public class ThreadA {
//    به بیان ساده، فراخوانی ()waite رشته فعلی را مجبور می‌کند تا منتظر بماند تا thread دیگری notify() یا notifyAll() را روی همان شیء فراخوانی کند.
//
//برای این کار، رشته فعلی باید مانیتور شی را داشته باشد. به گفته Javadocs، این می تواند به روش های زیر اتفاق بیفتد:
//
//زمانی که روش نمونه همگام سازی شده را برای شی داده شده اجرا کردیم
//زمانی که بدنه یک بلوک همگام شده را روی شی داده شده اجرا می کنیم
//با اجرای متدهای استاتیک همگام شده برای اشیاء از نوع Class
//توجه داشته باشید که هر بار فقط یک رشته فعال می تواند مالک مانیتور یک شی باشد.

//    وقتی از متد sleep() استفاده می کنیم، یک رشته پس از یک بازه زمانی مشخص شروع به کار می کند، مگر اینکه قطع شود.
//
//برای wait()، فرآیند بیدار شدن کمی پیچیده تر است. می‌توانیم با فراخوانی متدهای notify() یا notifyAll() روی مانیتوری که در حال انتظار است، موضوع را بیدار کنیم.
//
//هنگامی که می خواهید تمام رشته هایی را که در حالت انتظار هستند، بیدار کنید، به جای notify() از notifyAll() استفاده کنید. مانند خود متد wait()، notify() و notifyAll() باید از زمینه همگام سازی شده فراخوانی شوند.

    private static final Logger LOG= LoggerFactory.getLogger(ThreadA.class);

    private static final ThreadB b=new ThreadB();

    public static void main(String... args) throws InterruptedException {
        b.start();
        synchronized (b){
            while(b.sum==0){
                LOG.debug("Waiting for ThreadB to complete...");
//                متد wait() باعث می‌شود تا رشته فعلی به‌طور نامحدود منتظر بماند تا رشته‌ای دیگر یا notify() را برای این شیء فراخوانی کند یا notifyAll().
                b.wait();
            }
            LOG.debug("ThreadB has completed.Sum from that thread is: " +b.sum);
        }

    }
}
