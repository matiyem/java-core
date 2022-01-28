package com.example.concurrent.threads.number;

import java.lang.management.ManagementFactory;

/*
    Create by Atiye Mousavi 
    Date: 1/25/2022
    Time: 10:47 AM
**/
public class FindNumberOfThreads {

    public static void main(String[] args) {
        //برای پیدا کردن تعداد thread های active
//        اگر گروه های رشته ای زیادی در یک برنامه جاوا وجود داشته باشد، activeCount() خروجی درستی نمی دهد. برای مثال، تعداد رشته‌های GC را برنمی‌گرداند.
        System.out.println("Number of threads " + Thread.activeCount());
        System.out.println("Current Thread Group - "+Thread.currentThread().getThreadGroup().getName());
//        این API تعداد کل رشته ها را از همه گروه های رشته، GC، JMX و غیره برمی گرداند:این API تعداد کل رشته ها را از همه گروه های رشته، GC، JMX و غیره برمی گرداند:
        System.out.println("Total Number of threads "+ ManagementFactory.getThreadMXBean().getThreadCount());
    }
}
