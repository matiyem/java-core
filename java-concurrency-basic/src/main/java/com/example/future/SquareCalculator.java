package com.example.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 9:57 AM
**/
public class SquareCalculator {
    private final ExecutorService executor;
//    بیت کدی که در واقع محاسبه را انجام می دهد در متد call() موجود است و به عنوان یک عبارت لامبدا ارائه می شود. همانطور که می بینیم، هیچ چیز خاصی در مورد آن وجود ندارد، به جز فراخوانی sleep() که قبلا ذکر شد.
//
//وقتی توجه خود را به استفاده از Callable و ExecutorService معطوف می کنیم جالب تر می شود.
//
//Callable یک رابط است که وظیفه ای را نشان می دهد که یک نتیجه را برمی گرداند و دارای یک متد call() تک است. در اینجا ما یک نمونه از آن را با استفاده از عبارت لامبدا ایجاد کرده ایم.
//
//ایجاد یک نمونه از Callable ما را به جایی نمی برد. ما هنوز باید این نمونه را به یک اجرا کننده منتقل کنیم که وظیفه شروع کار را در یک رشته جدید بر عهده دارد و شیء ارزشمند Future را به ما برمی گرداند. اینجاست که ExecutorService وارد می شود.
//
//چند راه وجود دارد که ما می توانیم به یک نمونه ExecutorService دسترسی پیدا کنیم، و بیشتر آنها توسط متدهای کارخانه ایستا کلاس ابزار Executors ارائه می شوند. در این مثال، ما از newSingleThreadExecutor ()، استفاده کردیم که به ما یک ExecutorService می‌دهد که می‌تواند یک رشته را در یک زمان مدیریت کند.
//
//هنگامی که یک شی ExecutorService داریم، فقط باید submit() را فراخوانی کنیم و Callable خود را به عنوان آرگومان ارسال کنیم. سپس submit() کار را شروع می کند و یک شی FutureTask را که پیاده سازی رابط Future است برمی گرداند.

    public SquareCalculator(ExecutorService executor) {
        this.executor = executor;
    }

    public Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;

        });
    }
}
