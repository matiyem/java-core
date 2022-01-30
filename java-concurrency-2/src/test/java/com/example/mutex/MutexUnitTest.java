package com.example.mutex;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MutexUnitTest {
//    در یک برنامه چند رشته ای، ممکن است دو یا چند رشته به طور همزمان نیاز به دسترسی به یک منبع مشترک داشته باشند که منجر به رفتار غیرمنتظره می شود. نمونه‌هایی از این منابع مشترک، ساختارهای داده، دستگاه‌های ورودی-خروجی، فایل‌ها و اتصالات شبکه هستند.
//
//ما این سناریو را شرایط مسابقه می نامیم. و بخشی از برنامه که به منبع مشترک دسترسی دارد به عنوان بخش بحرانی شناخته می شود. بنابراین، برای جلوگیری از شرایط مسابقه، باید دسترسی به بخش بحرانی را همگام کنیم.
//
//mutex (یا حذف متقابل) ساده‌ترین نوع همگام‌کننده است - تضمین می‌کند که تنها یک رشته می‌تواند بخش مهم یک برنامه رایانه‌ای را در یک زمان اجرا کند.
//
//برای دسترسی به بخش بحرانی، یک نخ، mutex را دریافت می کند، سپس به بخش بحرانی دسترسی پیدا می کند و در نهایت mutex را آزاد می کند. در همین حال، تمام رشته‌های دیگر مسدود می‌شوند تا mutex آزاد شود. به محض اینکه یک رشته از بخش بحرانی خارج شود، یک رشته دیگر می تواند وارد بخش بحرانی شود.







    // @Test
    // This test verifies the race condition use case, it may pass or fail based on execution environment
    // Uncomment @Test to run it
    public void givenUnsafeSequenceGenerator_whenRaceCondition_thenUnexpectedBehavior() throws Exception {
        int count = 1000;
        Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGenerator(), count);
        Assert.assertNotEquals(count, uniqueSequences.size());
    }

    @Test
    public void givenSequenceGeneratorUsingSynchronizedMethod_whenRaceCondition_thenSuccess() throws Exception {
        int count = 1000;
        Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingSynchronizedMethod(), count);
        Assert.assertEquals(count, uniqueSequences.size());
    }

    @Test
    public void givenSequenceGeneratorUsingSynchronizedBlock_whenRaceCondition_thenSuccess() throws Exception {
        int count = 1000;
        Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingSynchronizedBlock(), count);
        Assert.assertEquals(count, uniqueSequences.size());
    }

    @Test
    public void givenSequenceGeneratorUsingReentrantLock_whenRaceCondition_thenSuccess() throws Exception {
        int count = 1000;
        Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingReentrantLock(), count);
        Assert.assertEquals(count, uniqueSequences.size());
    }

    @Test
    public void givenSequenceGeneratorUsingSemaphore_whenRaceCondition_thenSuccess() throws Exception {
        int count = 1000;
        Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingSemaphore(), count);
        Assert.assertEquals(count, uniqueSequences.size());
    }

    @Test
    public void givenSequenceGeneratorUsingMonitor_whenRaceCondition_thenSuccess() throws Exception {
        int count = 1000;
        Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingMonitor(), count);
        Assert.assertEquals(count, uniqueSequences.size());
    }

    private Set<Integer> getUniqueSequences(SequenceGenerator generator, int count) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Set<Integer> uniqueSequences = new LinkedHashSet<>();
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            futures.add(executor.submit(generator::getNextSequence));
        }

        for (Future<Integer> future : futures) {
            uniqueSequences.add(future.get());
        }

        executor.awaitTermination(1, TimeUnit.SECONDS);
        executor.shutdown();

        return uniqueSequences;
    }

}
