package com.example.concurrent.priorityTaskExecution;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 3:21 PM
 **/


public class PriorityJobScheduler {
//    با تمام تنظیمات انجام شده، بیایید اکنون یک زمانبندی کار ساده را پیاده سازی کنیم - که از یک مجری رشته تکی برای جستجوی کارها در PriorityBlockingQueue و اجرای آنها استفاده می کند:
//    نکته کلیدی در اینجا ایجاد یک نمونه از PriorityBlockingQueue از نوع Job با یک مقایسه کننده سفارشی است. کار بعدی برای اجرا با استفاده از متد take() از صف انتخاب می شود که سر صف را بازیابی و حذف می کند.
//
//اکنون کد کلاینت به سادگی نیاز به فراخوانی ()schemeJob دارد - که کار را به صف اضافه می کند. PriorityQueue.add () با استفاده از JobExecutionComparator، کار را در موقعیت مناسب در مقایسه با کارهای موجود در صف قرار می دهد.
//    توجه داشته باشید که کارهای واقعی با استفاده از یک ExecutorService جداگانه با یک thread pool اختصاصی اجرا می شوند.
    private ExecutorService priorityJobPoolExecutor;
    private ExecutorService priorityJobScheduler = Executors.newSingleThreadExecutor();
    private PriorityBlockingQueue<Job> priorityQueue;

    public PriorityJobScheduler(Integer poolSize, Integer queueSize) {
        priorityJobPoolExecutor = Executors.newFixedThreadPool(poolSize);
        priorityQueue = new PriorityBlockingQueue<Job>(queueSize, Comparator.comparing(Job::getJobPriority));

        priorityJobScheduler.execute(() -> {
            while (true) {
                try {
                    priorityJobScheduler.execute(priorityQueue.take());
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
    }

    public void scheduleJob(Job job) {
        priorityQueue.add(job);
    }

    public int getQueuedTaskCount() {
        return priorityQueue.size();
    }
    protected void close(ExecutorService scheduler){
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)){
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void closeScheduler(){
        close(priorityJobPoolExecutor);
        close(priorityJobScheduler);
    }

}
