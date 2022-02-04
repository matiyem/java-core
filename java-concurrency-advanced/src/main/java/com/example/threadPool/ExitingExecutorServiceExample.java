package com.example.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.MoreExecutors;


/*
    Create by Atiye Mousavi 
    Date: 2/1/2022
    Time: 10:34 AM
**/
public class ExitingExecutorServiceExample {
    public static void main(String... args) {
        final ThreadPoolExecutor executor= (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        final ExecutorService executorService = MoreExecutors.getExitingExecutorService(executor, 100, TimeUnit.MILLISECONDS);

        executorService.submit((Runnable) () -> {
            while (true) {
            }
        });
    }
}
