package com.example.parallel;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.assertThat;

class ForkJoinUnitTest {
    //استریم های sequential بصورت تک thread کار میکنند و همیشه خروجی یکسان است
//    جریان های موازی ما را قادر می سازند تا کد را به صورت موازی روی هسته های جداگانه اجرا کنیم. نتیجه نهایی ترکیبی از هر نتیجه فردی است.
//
//اما دستور اجرا خارج از کنترل ماست. هر بار که برنامه را اجرا می کنیم ممکن است تغییر کند:
//    جریان های موازی از چارچوب fork-join و استخر مشترک آن از worker thread استفاده می کنند.
//    چارچوب fork-join به java.util.concurrent در جاوا 7 اضافه شد تا مدیریت وظایف بین رشته های مختلف را مدیریت کند.

    @Test
    void givenSequentialStreamOfNumbers_whenReducingSumWithIdentityFive_thenResultIsCorrect() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        int sum = listOfNumbers.stream().reduce(5, Integer::sum);
        assertThat(sum).isEqualTo(15);
    }

    @Test
    void givenParallelStreamOfNumbers_whenReducingSumWithIdentityFive_thenResultIsNotCorrect() {
//        در یک جریان متوالی، نتیجه این عملیات 15 خواهد بود.
//
//اما از آنجایی که عملیات کاهش به صورت موازی انجام می شود، عدد پنج در واقع در هر نخ کارگر جمع می شود:
//        نتیجه واقعی ممکن است بسته به تعداد رشته‌های مورد استفاده در استخر مشترک چنگال متفاوت باشد.
//
//برای رفع این مشکل، عدد پنج باید خارج از جریان موازی اضافه شود:
//        بنابراین، ما باید مراقب باشیم که کدام عملیات را می توان به صورت موازی اجرا کرد.
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        int sum = listOfNumbers.parallelStream().reduce(5, Integer::sum);
        assertThat(sum).isNotEqualTo(15);
    }

    @Test
    void givenParallelStreamOfNumbers_whenReducingSumWithIdentityZero_thenResultIsCorrect() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        int sum = listOfNumbers.parallelStream().reduce(0, Integer::sum) + 5;
        assertThat(sum).isEqualTo(15);
    }

    @Test
    public void givenParallelStreamOfNumbers_whenUsingCustomThreadPool_thenResultIsCorrect()
            throws InterruptedException, ExecutionException {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        ForkJoinPool customThreadPool = new ForkJoinPool(4);
        //اگر عدد idntity را غیر صفر بگذاریم تعداد thread در identity ضرب میشود و به عدد کل اضافه میشود
        int sum = customThreadPool.submit(
                () -> listOfNumbers.parallelStream().reduce(0, Integer::sum)).get();
        customThreadPool.shutdown();
        assertThat(sum).isEqualTo(10);
    }

}
