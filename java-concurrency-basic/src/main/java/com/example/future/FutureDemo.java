package com.example.future;

import java.util.concurrent.*;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 9:17 AM
**/
public class FutureDemo {
    public String invoke() {
        String str = null;
        //یک threadpool با 10 thread ایجاد میکند
        //ExecutorService می تواند وظایف Runnable و Callable را اجرا کند. برای ساده نگه داشتن موارد در این مقاله، از دو وظیفه ابتدایی استفاده می شود. توجه داشته باشید که ما در اینجا به جای کلاس های داخلی ناشناس از عبارات لامبدا استفاده می کنیم:
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<String> future = executorService.submit(() -> {
            //Task
            Thread.sleep(10000l);
            return "Hellow world";
        });
        future.cancel(false);

        try {
//            همچنین می‌توانیم برای یک عملیات معین یک بازه زمانی تعیین کنیم. اگر کار بیش از این زمان طول بکشد، TimeoutException پرتاب می شود:
            future.get(20, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        if (future.isDone() && !future.isCancelled()) {
            try {
                str = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return str;
    }
}
