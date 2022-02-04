package com.example.threadpool;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GuavaThreadPoolIntegrationTest {

    @Test
    public void whenExecutingTaskWithDirectExecutor_thenTheTaskIsExecutedInTheCurrentThread() {
//گاهی اوقات بسته به شرایطی، می‌خواهیم وظیفه را در رشته فعلی یا در یک Thread Pool اجرا کنیم. ما ترجیح می دهیم از یک رابط Executor استفاده کنیم و فقط پیاده سازی را تغییر دهیم. اگرچه ایجاد یک پیاده‌سازی Executor یا ExecutorService که وظایف موجود در رشته فعلی را اجرا می‌کند چندان سخت نیست، اما این هنوز نیاز به نوشتن کدهای دیگ بخار دارد.
//
//با خوشحالی، گواوا موارد از پیش تعریف شده را برای ما فراهم می کند.
//
//در اینجا مثالی وجود دارد که اجرای یک وظیفه در همان رشته را نشان می دهد. اگرچه وظیفه ارائه شده به مدت 500 میلی ثانیه می‌خوابد، رشته فعلی را مسدود می‌کند و نتیجه بلافاصله پس از اتمام فراخوانی در دسترس است:
        Executor executor = MoreExecutors.directExecutor();

        AtomicBoolean executed = new AtomicBoolean();

        executor.execute(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executed.set(true);
        });

        assertTrue(executed.get());
    }

    @Test
    public void whenJoiningFuturesWithAllAsList_thenCombinedFutureCompletesAfterAllFuturesComplete() throws ExecutionException, InterruptedException {
//دکوراتورهای Listening به ما این امکان را می دهند که ExecutorService را بپیچانیم و نمونه های ListenableFuture را پس از ارسال کار به جای نمونه های ساده Future دریافت کنیم. رابط ListenableFuture Future را گسترش می دهد و دارای یک روش اضافی addListener است. این روش امکان اضافه کردن شنونده ای را فراهم می کند که پس از تکمیل آینده فراخوانی می شود.
//
//ما به ندرت می خواهیم مستقیماً از متد ListenableFuture.addListener استفاده کنیم. اما برای بسیاری از متدهای کمکی در کلاس ابزار Futures ضروری است.
//
//به عنوان مثال، با متد Futures.allAsList() می‌توانیم چندین نمونه ListenableFuture را در یک ListenableFuture ترکیب کنیم که با تکمیل موفقیت‌آمیز تمام فیوچرهای ترکیبی تکمیل می‌شود:
        ExecutorService executorService = Executors.newCachedThreadPool();
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        ListenableFuture<String> future1 = listeningExecutorService.submit(() -> "Hello");
        ListenableFuture<String> future2 = listeningExecutorService.submit(() -> "World");

        String greeting = Futures.allAsList(future1, future2).get().stream().collect(Collectors.joining(" "));
        assertEquals("Hello World", greeting);

    }

}
