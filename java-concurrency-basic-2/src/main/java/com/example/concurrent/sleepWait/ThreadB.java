package com.example.concurrent.sleepWait;

/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 12:44 PM
**/
public class ThreadB extends Thread {
//    وقتی از متد sleep() استفاده می کنیم، یک رشته پس از یک بازه زمانی مشخص شروع به کار می کند، مگر اینکه قطع شود.
//
//برای wait()، فرآیند بیدار شدن کمی پیچیده تر است. می‌توانیم با فراخوانی متدهای notify() یا notifyAll() روی مانیتوری که در حال انتظار است، موضوع را بیدار کنیم.
//
//هنگامی که می خواهید تمام رشته هایی را که در حالت انتظار هستند، بیدار کنید، به جای notify() از notifyAll() استفاده کنید. مانند خود متد wait()، notify() و notifyAll() باید از زمینه همگام سازی شده فراخوانی شوند.
    int sum;

    @Override
    public void run() {
        synchronized (this) {
//            و سپس، در اینجا آمده است که چگونه یک رشته دیگر می تواند رشته منتظر را بیدار کند - با فراخوانی notify() در مانیتور:
            int i = 0;
            while (i < 100000) {
                sum += i;
                i++;
            }
            notify();
        }
    }
}
