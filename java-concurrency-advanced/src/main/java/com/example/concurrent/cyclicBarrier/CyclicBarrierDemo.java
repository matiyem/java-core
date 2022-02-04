package com.example.concurrent.cyclicBarrier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
    Create by Atiye Mousavi 
    Date: 1/30/2022
    Time: 3:59 PM
**/
public class CyclicBarrierDemo {
//    CyclicBarriers ساختارهای همگام سازی هستند که با جاوا 5 به عنوان بخشی از بسته java.util.concurrent معرفی شدند.
//    CyclicBarrier یک همگام‌سازی است که به مجموعه‌ای از رشته‌ها اجازه می‌دهد تا منتظر یکدیگر باشند تا به یک نقطه اجرای مشترک برسند که به آن مانع نیز می‌گویند.
//
//CyclicBarriers در برنامه هایی استفاده می شود که در آنها تعداد ثابتی از Thread ها داریم که قبل از ادامه اجرا باید منتظر بمانند تا یکدیگر به یک نقطه مشترک برسند.
//
//این مانع چرخه ای نامیده می شود زیرا می توان پس از آزاد شدن نخ های انتظار دوباره از آن استفاده کرد.


//    برای مشاهده عمل CyclicBarrier، سناریوی زیر را در نظر بگیرید:
//
//عملیاتی وجود دارد که تعداد ثابتی از رشته ها انجام می شود و نتایج مربوطه را در یک لیست ذخیره می کند. زمانی که تمام رشته‌ها عملکرد خود را به پایان می‌رسانند، یکی از آنها (معمولاً آخرین موردی که مانع را از بین می‌برد) شروع به پردازش داده‌هایی می‌کند که توسط هر یک از آنها واکشی شده است.
//
//بیایید کلاس اصلی را پیاده سازی کنیم که در آن تمام اقدامات انجام می شود:

//    این کلاس کاملاً مستقیم است - NUM_WORKERS تعداد رشته‌هایی است که قرار است اجرا شوند و NUM_PARTIAL_RESULTS تعداد نتایجی است که هر یک از رشته‌های کارگر تولید می‌کنند.
//
//در نهایت، partalResults داریم که لیستی است که نتایج هر یک از این رشته های کارگر را ذخیره می کند. توجه داشته باشید که این لیست یک SynchronizedList است زیرا چندین رشته همزمان در آن می نویسند و متد add() در یک ArrayList ساده ایمن نیست.
//
//حال بیایید منطق هر یک از موضوعات کارگر را پیاده سازی کنیم:
    private CyclicBarrier cyclicBarrier;
    private List<List<Integer>> partialResults = Collections.synchronizedList(new ArrayList<>());
    private Random random = new Random();
    private int NUM_PARTIAL_RESULTS;
    private int NUM_WORKER;

    private void runSimulation(int numWorker, int numberOfPartialResults) {
        NUM_PARTIAL_RESULTS = numberOfPartialResults;
        NUM_WORKER = numWorker;
//        سازنده برای CyclicBarrier ساده است. یک عدد صحیح می‌گیرد که نشان‌دهنده تعداد رشته‌هایی است که باید متد await() را در نمونه مانع فراخوانی کنند تا به معنای رسیدن به نقطه اجرای مشترک باشد:
        cyclicBarrier = new CyclicBarrier(NUM_WORKER, new AggregatorThread());
        System.out.println("Spawning " + NUM_WORKER + " worker threads to compute " + NUM_PARTIAL_RESULTS + " partial results each");
        for (int i = 0; i < NUM_WORKER; i++) {
            Thread worker = new Thread(new NumberCruncherThread());
            worker.setName("Thread " + i);
            worker.start();
        }
    }

    class NumberCruncherThread implements Runnable {

        @Override
        public void run() {
            String thisThreadName = Thread.currentThread().getName();
            List<Integer> partialResult = new ArrayList<>();
            for (int i = 0; i < NUM_PARTIAL_RESULTS; i++) {
                Integer num = random.nextInt(10);
                System.out.println(thisThreadName + ": Crunching some numbers! Final result - " + num);
                partialResult.add(num);
            }
            partialResults.add(partialResult);
            try {
                System.out.println(thisThreadName + " waiting for others to reach barrier.");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();

            }

        }
    }

    class AggregatorThread implements Runnable {
//        اکنون منطقی را اجرا می کنیم که وقتی مانع از بین رفت اجرا می شود.
//
//برای ساده نگه داشتن همه چیز، بیایید فقط تمام اعداد را در لیست نتایج جزئی اضافه کنیم:

        @Override
        public void run() {
            String thisThreadName = Thread.currentThread().getName();
            System.out.println(thisThreadName + ": Computing final sum of " + NUM_WORKER + " workers, having " + NUM_PARTIAL_RESULTS + " results each.");
            int sum = 0;
            for (List<Integer> threadResult : partialResults) {
                System.out.println("Adding ");
                for (Integer partialResult : threadResult) {
                    System.out.println(partialResult + " ");
                    sum += partialResult;
                }
                System.out.println();
            }
            System.out.println(Thread.currentThread().getName() + ": Final result = " + sum);
        }
    }

    public static void main(String[] args) {
//        مرحله نهایی ساخت CyclicBarrier و شروع کار با متد main() است:
        CyclicBarrierDemo play = new CyclicBarrierDemo();
//        در کد بالا، سد چرخه‌ای را با 5 رشته تنظیم کردیم که هر کدام 3 عدد صحیح را به عنوان بخشی از محاسبات خود تولید می‌کنند و همان را در لیست حاصل ذخیره می‌کنند.
//
//هنگامی که مانع قطع شد، آخرین رشته ای که مانع را متوقف کرد، منطق مشخص شده در AggregatorThread را اجرا می کند، یعنی - تمام اعداد تولید شده توسط رشته ها را اضافه کنید.
        play.runSimulation(5, 3);

//        در اینجا خروجی یک اجرای برنامه فوق آمده است - هر اجرا ممکن است نتایج متفاوتی ایجاد کند زیرا رشته ها می توانند به ترتیب متفاوتی ایجاد شوند:
    }
}
