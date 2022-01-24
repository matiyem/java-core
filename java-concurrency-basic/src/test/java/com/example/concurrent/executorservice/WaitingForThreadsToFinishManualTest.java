package com.example.concurrent.executorservice;

import com.example.executorService.DelayedCallable;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static junit.framework.TestCase.assertTrue;

public class WaitingForThreadsToFinishManualTest {

    private static final Logger LOG = LoggerFactory.getLogger(WaitingForThreadsToFinishManualTest.class);
    private final static ExecutorService WORKER_THREAD_POOL = Executors.newFixedThreadPool(10);

    public void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    
    @Test
    public void givenMultipleThreads_whenUsingCountDownLatch_thenMainShoudWaitForAllToFinish() {
//    هنگام استفاده از Executor، می‌توانیم آن را با فراخوانی متدهای shutdown() یا shutdownNow() خاموش کنیم. اگرچه، تا زمانی که اجرای همه رشته ها متوقف شود، صبر نمی کند.
//
//با استفاده از متد awaitTermination() می توان منتظر ماند تا رشته های موجود اجرا شوند.
//
//این موضوع تا زمانی که همه وظایف اجرای خود را کامل کنند یا به مهلت زمانی مشخص شده برسد مسدود می کند:

        ExecutorService WORKER_THREAD_POOL = Executors.newFixedThreadPool(10);
        
        try {
            long startTime = System.currentTimeMillis();
//            CountDownLatch (معرفی شده در JDK 5) یک کلاس کاربردی است که مجموعه‌ای از رشته‌ها را تا زمانی که برخی عملیات تکمیل شود مسدود می‌کند.
//
//یک CountDownLatch با یک شمارنده (نوع عدد صحیح) مقداردهی اولیه می شود. این شمارنده با اجرای کامل رشته های وابسته کاهش می یابد. اما هنگامی که شمارنده به صفر می رسد، رشته های دیگر آزاد می شوند.
//
            CountDownLatch latch = new CountDownLatch(2);
            
            for (int i = 0; i < 2; i++) {
                WORKER_THREAD_POOL.submit(() -> {
                    try {
                        Thread.sleep(1000);
                        latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                });
            }

            // wait for the latch to be decremented by the two threads
            latch.await();

            long processingTime = System.currentTimeMillis() - startTime;
            assertTrue(processingTime >= 1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        awaitTerminationAfterShutdown(WORKER_THREAD_POOL);
    }

    @Test
    public void givenMultipleThreads_whenInvokeAll_thenMainThreadShouldWaitForAllToFinish() {
//        اولین رویکردی که می توانیم برای اجرای thread ها استفاده کنیم، متد ()invokeAll است. این متد لیستی از اشیاء Future را پس از اتمام همه کارها یا انقضای مهلت بازمی گرداند.
//
//همچنین، باید توجه داشته باشیم که ترتیب اشیاء Future برگشتی مانند لیست اشیاء Callable ارائه شده است:
        ExecutorService WORKER_THREAD_POOL = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = Arrays.asList(
            new DelayedCallable("fast thread", 100),
            new DelayedCallable("slow thread", 3000));

        try {
            long startProcessingTime = System.currentTimeMillis();
//            متدهای submit() و invokeAll() یک شی یا مجموعه ای از اشیاء از نوع Future را برمی گرداند، که به ما امکان می دهد نتیجه اجرای یک کار را بدست آوریم یا وضعیت آن را بررسی کنیم (آیا در حال اجرا است).
//
//اینترفیس Future یک متد مسدودکننده خاص (() را ارائه می‌کند، که یک نتیجه واقعی از اجرای کار Callable یا در مورد یک کار Runnable، null را برمی‌گرداند:
            List<Future<String>> futures = WORKER_THREAD_POOL.invokeAll(callables);
            
            awaitTerminationAfterShutdown(WORKER_THREAD_POOL);

            try {
//                متدهای submit() و invokeAll() یک شی یا مجموعه ای از اشیاء از نوع Future را برمی گرداند، که به ما امکان می دهد نتیجه اجرای یک کار را بدست آوریم یا وضعیت آن را بررسی کنیم (آیا در حال اجرا است).
//
//اینترفیس Future یک متد مسدودکننده خاص (() را ارائه می‌کند، که یک نتیجه واقعی از اجرای کار Callable یا در مورد یک کار Runnable، null را برمی‌گرداند:
                WORKER_THREAD_POOL.submit((Callable<String>) () -> {
                    Thread.sleep(1000000);
                    return null;
                });
            } catch (RejectedExecutionException ex) {

            }

            long totalProcessingTime = System.currentTimeMillis() - startProcessingTime;
            assertTrue(totalProcessingTime >= 3000);

//فراخوانی متد get() در حالی که کار هنوز در حال اجرا است باعث می شود تا اجرا به درستی اجرا شود و نتیجه در دسترس باشد.
//
//با انسداد بسیار طولانی که توسط متد get() ایجاد می شود، عملکرد یک برنامه می تواند کاهش یابد. اگر داده‌های به‌دست‌آمده حیاتی نیستند، می‌توان با استفاده از بازه‌های زمانی از چنین مشکلی جلوگیری کرد:
            String firstThreadResponse = futures.get(0).get();
            assertTrue("First response should be from the fast thread", "fast thread".equals(firstThreadResponse));

            String secondThreadResponse = futures.get(1).get();
            assertTrue("Last response should be from the slow thread", "slow thread".equals(secondThreadResponse));

        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }       
    }

    @Test
    public void givenMultipleThreads_whenUsingCompletionService_thenMainThreadShouldWaitForAllToFinish() {
////        اولین رویکردی که می توانیم برای اجرای thread ها استفاده کنیم، متد ()invokeAll است. این متد لیستی از اشیاء Future را پس از اتمام همه کارها یا انقضای مهلت بازمی گرداند.
//
////همچنین، باید توجه داشته باشیم که ترتیب اشیاء Future برگشتی مانند لیست اشیاء Callable ارائه شده است:
        CompletionService<String> service = new ExecutorCompletionService<>(WORKER_THREAD_POOL);

        List<Callable<String>> callables = Arrays.asList(
            new DelayedCallable("fast thread", 100), 
            new DelayedCallable("slow thread", 3000));

        for (Callable<String> callable : callables) {
            service.submit(callable);
        }

        try {

            long startProcessingTime = System.currentTimeMillis();

            Future<String> future = service.take();
            String firstThreadResponse = future.get();
            long totalProcessingTime = System.currentTimeMillis() - startProcessingTime;

            assertTrue("First response should be from the fast thread", "fast thread".equals(firstThreadResponse));
            assertTrue(totalProcessingTime >= 100 && totalProcessingTime < 1000);
            LOG.debug("Thread finished after: " + totalProcessingTime + " milliseconds");

            future = service.take();
            String secondThreadResponse = future.get();
            totalProcessingTime = System.currentTimeMillis() - startProcessingTime;

            assertTrue("Last response should be from the slow thread", "slow thread".equals(secondThreadResponse));
            assertTrue(totalProcessingTime >= 3000 && totalProcessingTime < 4000);
            LOG.debug("Thread finished after: " + totalProcessingTime + " milliseconds");

        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            awaitTerminationAfterShutdown(WORKER_THREAD_POOL);
        }
    }
}
