package com.example.threadSafety.callables;

import com.example.threadSafety.service.AtomicCounter;

import java.util.concurrent.Callable;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 1:02 PM
**/
public class AtomicCounterCallable implements Callable<Integer> {
//    واسط Callable یک رابط عمومی است که شامل یک متد call() تک است – که مقدار عمومی V را برمی‌گرداند:
//    متد call()Callable حاوی عبارت “throws Exception” است تا بتوانیم به راحتی استثناهای بررسی شده را بیشتر منتشر کنیم:

    private final AtomicCounter counter;

    public AtomicCounterCallable(AtomicCounter counter) {
        this.counter = counter;
    }

    @Override
    public Integer call() throws Exception {
        counter.incrementCounter();
        return counter.getCounter();
    }
}
