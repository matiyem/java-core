package com.example.concurrent.callable;

import com.example.callable.FactorialTask;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static junit.framework.Assert.assertEquals;

public class FactorialTaskManualTest {

    private ExecutorService executorService;

    @Before
    public void setup(){
//        ScheduledExecutorService وظایف را پس از مدتی تاخیر از پیش تعریف شده و/یا به صورت دوره ای اجرا می کند.
//
//یک بار دیگر، بهترین راه برای نمونه سازی ScheduledExecutorService استفاده از متدهای کارخانه ای کلاس Executors است.
//
//برای این بخش، ما از یک ScheduledExecutorService با یک رشته استفاده می کنیم:
        executorService = Executors.newSingleThreadExecutor();
    }

    @Test
    public void whenTaskSubmitted_ThenFutureResultObtained() throws ExecutionException, InterruptedException {
        FactorialTask task = new FactorialTask(5);
        Future<Integer> future= executorService.submit(task);
        assertEquals(120,future.get().intValue());
    }

    @Test(expected = ExecutionException.class)
    public void whenException_ThenCallableThrowsIt() throws ExecutionException, InterruptedException {
        //        در صورت اجرای Callable با استفاده از ExecutorService، استثناها در شی Future جمع آوری می شوند که با فراخوانی متد Future.get() قابل بررسی است. این یک ExecutionException ایجاد می کند - که استثنای اصلی را می پیچد:
//        در تست بالا، ExecutionException پرتاب می شود زیرا ما در حال عبور از یک عدد نامعتبر هستیم. می‌توانیم متد getCause() را در این شیء استثنا فراخوانی کنیم تا استثنای اصلی بررسی شده را بدست آوریم.
//
//اگر فراخوانی متد get() از کلاس Future را انجام ندهیم – آنگاه استثناء پرتاب شده توسط متد call() پس داده نمی شود و کار همچنان به عنوان تکمیل شده علامت گذاری می شود:
        FactorialTask task = new FactorialTask(-5);
        Future<Integer> future= executorService.submit(task);
        Integer result=future.get().intValue();
    }

    @Test
    public void whenException_ThenCallableDoesntThrowsItIfGetIsNotCalled(){
//        تست فوق با موفقیت انجام می شود، حتی اگر یک استثنا برای مقادیر منفی پارامتر در FactorialCallableTask قرار داده باشیم.
        FactorialTask task = new FactorialTask(-5);
        Future<Integer> future= executorService.submit(task);
        assertEquals(false,future.isDone());
    }

    @After
    public void cleanup(){
        executorService.shutdown();
    }
}
