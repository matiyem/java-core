package com.example.concurrent.executorservice;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class Java8ExecutorServiceIntegrationTest {

    private Runnable runnableTask;
    private Callable<String> callableTask;
    private List<Callable<String>> callableTasks;

    @Before
    public void init() {
//        ما می‌توانیم با استفاده از چندین متد از جمله execute()، که از رابط Executor به ارث برده شده است، و همچنین submit()، invokeAny() و invokeAll() وظایفی را به ExecutorService اختصاص دهیم.
//
//متد execute() باطل است و هیچ امکانی را برای دریافت نتیجه اجرای یک کار یا بررسی وضعیت آن (آیا در حال اجرا است) نمی دهد:
        runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "Task's execution";
        };

        callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
    }

    @Test
    public void creationSubmittingTaskShuttingDown_whenShutDown_thenCorrect() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        () submit یک وظیفه Callable یا Runnable را به ExecutorService ارسال می کند و نتیجه ای از نوع Future را برمی گرداند:
        executorService.submit(runnableTask);
        executorService.submit(callableTask);
        executorService.shutdown();

        assertTrue(executorService.isShutdown());
    }

    @Test
    public void creationSubmittingTasksShuttingDownNow_whenShutDownAfterAwating_thenCorrect() {
//        در برنامه نویسی ناهمزمان، یکی از رایج ترین الگوهای ادغام، الگوی تولیدکننده-مصرف کننده است. بسته java.util.concurrent با ساختار داده ای به نام BlockingQueue ارائه می شود - که می تواند در این سناریوهای ناهمگام بسیار مفید باشد.

        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.submit(callableTask);
        }

        List<Runnable> notExecutedTasks = smartShutdown(threadPoolExecutor);

        assertTrue(threadPoolExecutor.isShutdown());
        assertFalse(notExecutedTasks.isEmpty());
        assertTrue(notExecutedTasks.size() < 98);
    }

    private List<Runnable> smartShutdown(ExecutorService executorService) {

        List<Runnable> notExecutedTasks = new ArrayList<>();
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                notExecutedTasks = executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            notExecutedTasks = executorService.shutdownNow();
        }
        return notExecutedTasks;
    }

    @Test
    public void submittingTasks_whenExecutedOneAndAll_thenCorrect() {


        ExecutorService executorService = Executors.newFixedThreadPool(10);

        String result = null;
        List<Future<String>> futures = new ArrayList<>();
        try {
//            invokeAny () مجموعه‌ای از وظایف را به ExecutorService اختصاص می‌دهد و باعث می‌شود هر کدام اجرا شوند و نتیجه اجرای موفقیت‌آمیز یک کار (در صورت اجرای موفقیت‌آمیز) را برمی‌گرداند:
            result = executorService.invokeAny(callableTasks);
//            invokeAll() مجموعه ای از وظایف را به یک ExecutorService اختصاص می دهد و باعث می شود هر کدام اجرا شوند و نتیجه همه اجرای وظایف را در قالب لیستی از اشیاء از نوع Future برمی گرداند:
            futures = executorService.invokeAll(callableTasks);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        assertEquals("Task's execution", result);
        assertTrue(futures.size() == 3);
    }

    @Test
    public void submittingTaskShuttingDown_whenGetExpectedResult_thenCorrect() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<String> future = executorService.submit(callableTask);
        String result = null;
        try {
            result = future.get();
            result = future.get(200, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        assertEquals("Task's execution", result);
    }

    @Test
    public void submittingTask_whenCanceled_thenCorrect() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<String> future = executorService.submit(callableTask);
//اگر دوره اجرا بیشتر از مقدار مشخص شده باشد  یک TimeoutException پرتاب می شود.
//
//ما می توانیم از متد isDone() برای بررسی اینکه آیا وظیفه اختصاص داده شده قبلا پردازش شده است یا خیر استفاده کنیم.
//
//رابط Future همچنین امکان لغو اجرای کار با متد cancel() و بررسی لغو با متد isCancelled() را فراهم می کند:
        boolean canceled = future.cancel(true);
        boolean isCancelled = future.isCancelled();

        executorService.shutdown();

        assertTrue(canceled);
        assertTrue(isCancelled);
    }

    @Test
    public void submittingTaskScheduling_whenExecuted_thenCorrect() {

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Future<String> resultFuture = executorService.schedule(callableTask, 1, TimeUnit.SECONDS);
        String result = null;
        try {
            result = resultFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        assertEquals("Task's execution", result);
    }
}
