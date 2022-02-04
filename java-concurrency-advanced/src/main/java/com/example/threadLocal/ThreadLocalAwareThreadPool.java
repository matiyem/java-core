package com.example.threadLocal;

import java.util.concurrent.*;

/*
    Create by Atiye Mousavi 
    Date: 2/1/2022
    Time: 9:31 AM
**/
public class ThreadLocalAwareThreadPool extends ThreadPoolExecutor {
//    ThreadLocal یک API با کاربرد آسان برای محدود کردن برخی از مقادیر به هر رشته ارائه می دهد. این یک راه معقول برای دستیابی به ایمنی رشته در جاوا است. با این حال، زمانی که از ThreadLocals و Thread Pools با هم استفاده می کنیم، باید بیشتر مراقب باشیم.
//
//برای درک بهتر اخطار احتمالی، بیایید سناریوی زیر را در نظر بگیریم:
//
//ابتدا، برنامه یک رشته را از استخر قرض می گیرد.
//سپس مقداری از مقادیر محدود به رشته را در ThreadLocal رشته فعلی ذخیره می کند.
//پس از اتمام اجرای فعلی، برنامه موضوع قرض گرفته شده را به pool برمی گرداند.
//پس از مدتی، برنامه همان رشته را برای پردازش درخواست دیگری قرض می گیرد.
//از آنجایی که برنامه آخرین بار پاکسازی های لازم را انجام نداد، ممکن است از همان داده های ThreadLocal برای درخواست جدید دوباره استفاده کند.
//این ممکن است عواقب شگفت انگیزی را در برنامه های بسیار همزمان ایجاد کند.
//
//یکی از راه‌های حل این مشکل حذف دستی هر ThreadLocal پس از اتمام استفاده از آن است. از آنجایی که این رویکرد به بررسی دقیق کد نیاز دارد، ممکن است مستعد خطا باشد.

//    همانطور که مشخص است، می توان کلاس ThreadPoolExecutor را گسترش داد و یک پیاده سازی هوک سفارشی برای متدهای () BeforeExecute و AfterExecute() ارائه داد. Thread pool قبل از اجرای هر چیزی با استفاده از thread قرض گرفته شده، متد () BeforeExecute را فراخوانی می کند. از طرف دیگر، متد afterExecute() را پس از اجرای منطق ما فراخوانی می کند.
//
//بنابراین، می‌توانیم کلاس ThreadPoolExecutor را گسترش دهیم و داده‌های ThreadLocal را در متد afterExecute() حذف کنیم:
//    اگر درخواست‌های خود را برای اجرای ExecutorService ارسال کنیم، می‌توانیم مطمئن باشیم که استفاده از ThreadLocal و Thread Pool خطرات ایمنی را برای برنامه ما ایجاد نمی‌کند.

    public ThreadLocalAwareThreadPool(int corePoolSize,
                                      int maximumPoolSize,
                                      long keepAliveTime,
                                      TimeUnit unit,
                                      BlockingQueue<Runnable> workQueue,
                                      ThreadFactory threadFactory,
                                      RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {

    }
}
