package com.example.abaProblem;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;


/*
    Create by Atiye Mousavi 
    Date: 2/13/2022
    Time: 12:23 PM
**/
public class Account {
//    ابتدا، کلاس Account ما مقدار موجودی را در یک AtomicInteger نگه می‌دارد، که به ما CAS برای اعداد صحیح در جاوا می‌دهد. همچنین، AtomicInteger دیگری برای شمارش تعداد تراکنش‌های موفق وجود دارد. در نهایت، ما یک متغیر ThreadLocal برای ثبت شکست‌های عملیات CAS برای یک رشته معین داریم.

    //در نهایت می توانیم یک تست واحد بنویسیم تا بررسی کنیم که آیا مشکل ABA امکان پذیر است یا خیر.
//
//کاری که ما انجام خواهیم داد این است که دو رشته داشته باشیم، Thread 1 و Thread 2 از قبل. thread 1 تعادل را می خواند و به تاخیر می افتد. thread 2، در حالی که thread 1 در حالت خواب است، تعادل را تغییر می دهد و سپس دوباره آن را تغییر می دهد.
//
//هنگامی که Thread 1 بیدار شود، عاقلانه تر نخواهد بود و عملیات آن همچنان موفق خواهد بود.
//
//پس از مقداری مقداردهی اولیه، می‌توانیم Thread 1 را ایجاد کنیم که برای انجام عملیات CAS به زمان اضافی نیاز دارد. پس از اتمام آن، متوجه نمی شود که وضعیت داخلی تغییر کرده است، بنابراین تعداد خرابی CAS به جای 1 مورد انتظار در یک سناریوی ABA صفر خواهد بود:
    private final AtomicInteger balance;
    private final AtomicInteger transactionCount;
    private final ThreadLocal<Integer> currentThreadCASFailureCount;

    public Account() {
        this.balance = new AtomicInteger(0);
        this.transactionCount = new AtomicInteger(0);
        this.currentThreadCASFailureCount = ThreadLocal.withInitial(() -> 0);
    }

    public int getBalance() {
        return balance.get();
    }

    public int getTransactionCount() {
        return transactionCount.get();
    }

    public int getCurrentThreadCASFailureCount() {
        return currentThreadCASFailureCount.get();
    }

    public boolean withdraw(int amount) {
        int current = getBalance();
        maybeWait();
        boolean result = balance.compareAndSet(current, current - amount);
        if (result) {
            transactionCount.incrementAndGet();
        } else {
            int currentCASFailureCount = currentThreadCASFailureCount.get();
            currentThreadCASFailureCount.set(currentCASFailureCount + 1);
        }
        return result;
    }

    private void maybeWait() {
//        برای اینکه بتوانیم مشکل ABA را نشان دهیم، یک متد maybeWait() ایجاد کردیم تا برخی عملیات زمان‌بر را نگه دارد که مدت زمان بیشتری را برای رشته دیگر برای انجام تغییرات در تعادل فراهم می‌کند.
//
//اکنون، ما فقط Thread 1 را برای دو ثانیه به حالت تعلیق در می آوریم:
        if ("thread1".equals(Thread.currentThread().getName())) {
            sleepUninterruptibly(2, TimeUnit.SECONDS);
        }
    }

    public boolean deposit(int amount) {
        int current = balance.get();
        boolean result = balance.compareAndSet(current, current + amount);
        if (result) {
            transactionCount.incrementAndGet();
        } else {
            int currentCASFailureCount = currentThreadCASFailureCount.get();
            currentThreadCASFailureCount.set(currentCASFailureCount);
        }
        return result;
    }

}
