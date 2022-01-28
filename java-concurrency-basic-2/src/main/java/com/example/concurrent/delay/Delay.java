package com.example.concurrent.delay;

import java.util.concurrent.*;

/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 11:21 AM
**/
public class Delay {
//    با این حال، استفاده از این روش های مبتنی بر نخ دارای معایبی است:
//
//زمان‌های خواب دقیقاً دقیق نیستند، به‌خصوص زمانی که از افزایش‌های زمانی کوچک‌تر مانند میلی‌ثانیه و نانوثانیه استفاده می‌شود.
//هنگامی که در داخل حلقه ها استفاده می شود، خواب به دلیل اجرای کدهای دیگر، کمی بین تکرارهای حلقه جابجا می شود، بنابراین زمان اجرا ممکن است پس از تکرارهای زیاد نادقیق شود.
    public static void main(String[] args) {
        threadSleep(4,1);
        timeUnitSleep(4,1);
        delayedServiceTask(5);
        fixedRateServiceTask(5);
        System.out.println("Done.");
        return;
    }

    private static void threadSleep(Integer iterations, Integer secondsToSleep) {
        for (Integer i = 0; i < iterations; i++) {
            System.out.println("This is loop iteration number " + i.toString());

            try {
                Thread.sleep(secondsToSleep * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    private static void timeUnitSleep(Integer iterations, Integer secondsToSleep) {
        for (Integer i = 0; i < iterations; i++) {
            System.out.println("This is loop iteration number " + i.toString());
            try {
//                برای خوانایی بهتر، می‌توانیم از TimeUnit.XXX.sleep(y) استفاده کنیم، که در آن XXX واحد زمانی برای خواب (SECONDS، MINUTES، و غیره)
//                 و y تعداد آن واحد برای خوابیدن است. این از Thread.sleep در پشت صحنه استفاده می کند. در اینجا مثالی از نحو TimeUnit آورده شده است:
                TimeUnit.SECONDS.sleep(secondsToSleep);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void delayedServiceTask(Integer delayInSeconds) {
//        جاوا رابط ScheduledExecutorService را ارائه می دهد که راه حل قوی تر و دقیق تری است. این رابط می تواند کد را برنامه ریزی کند تا یک بار پس از یک تاخیر مشخص یا در فواصل زمانی مشخص اجرا شود.
//
//برای اجرای یک قطعه کد پس از تاخیر، می توانیم از روش زمانبندی استفاده کنیم:
//        قسمت Classname::someTask جایی است که روشی را مشخص می کنیم که بعد از تاخیر اجرا شود:
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(Delay::someTask1, delayInSeconds, TimeUnit.SECONDS);
        executorService.shutdown();
    }

    private static void fixedRateServiceTask(Integer delayInSecond) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//        برای اجرای یک کار در بازه های زمانی ثابت، می توانیم از روش scheduleAtFixedRate استفاده کنیم:
        ScheduledFuture<?> sf = executorService.scheduleAtFixedRate(Delay::someTask2, 0, delayInSecond, TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        sf.cancel(true);
        executorService.shutdown();
    }

    private static void someTask1() {
        System.out.println("Task 1 complete.");
    }

    private static void someTask2() {
        System.out.println("Task 2 completed.");
    }


}
