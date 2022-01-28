package com.example.synchronize;

import com.example.concurrent.synchronize.BaeldungSynchronizedMethods;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class BaeldungSynchronizeMethodsUnitTest {

    @Test
    @Ignore
    public void givenMultiThread_whenNonSyncMethod() throws InterruptedException {
//        ما از یک ExecutorService با یک استخر 3 رشته ای برای اجرای محاسبه() 1000 بار استفاده می کنیم.
//
//اگر این را به صورت سریال اجرا کنیم، خروجی مورد انتظار 1000 خواهد بود، اما اجرای چند رشته ای ما تقریباً هر بار با خروجی واقعی ناسازگار با شکست مواجه می شود:
//        البته ما این نتیجه را غیر منتظره نمی دانیم.
//
//یک راه ساده برای جلوگیری از شرایط مسابقه این است که با استفاده از کلمه کلیدی همگام‌سازی شده، عملیات را ایمن کنید.
        ExecutorService service = Executors.newFixedThreadPool(3);
        BaeldungSynchronizedMethods method = new BaeldungSynchronizedMethods();

        IntStream.range(0, 1000)
          .forEach(count -> service.submit(method::calculate));
        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, method.getSum());
    }

    @Test
    public void givenMultiThread_whenMethodSync() throws InterruptedException {
//        توجه داشته باشید که هنگامی که روش را همگام می کنیم، مورد تست با خروجی واقعی 1000 می گذرد:
//        متدهای نمونه بر روی نمونه کلاسی که متد را در اختیار دارد همگام می‌شوند، به این معنی که فقط یک رشته در هر نمونه از کلاس می‌تواند این متد را اجرا کند.
        ExecutorService service = Executors.newFixedThreadPool(3);
        BaeldungSynchronizedMethods method = new BaeldungSynchronizedMethods();

        IntStream.range(0, 1000)
          .forEach(count -> service.submit(method::synchronisedCalculate));
        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, method.getSyncSum());
    }

    @Test
    public void givenMultiThread_whenStaticSyncMethod() throws InterruptedException {
//        این متدها روی شی Class مرتبط با کلاس همگام می شوند. از آنجایی که فقط یک شی Class در هر JVM در هر کلاس وجود دارد، صرف نظر از تعداد نمونه‌هایی که دارد، فقط یک رشته می‌تواند در یک روش همگام‌سازی استاتیک در هر کلاس اجرا شود.
//
//بیایید آن را آزمایش کنیم:
        ExecutorService service = Executors.newCachedThreadPool();

        IntStream.range(0, 1000)
          .forEach(count -> service.submit(BaeldungSynchronizedMethods::syncStaticCalculate));
        service.awaitTermination(100, TimeUnit.MILLISECONDS);

        assertEquals(1000, BaeldungSynchronizedMethods.staticSum);
    }

}
