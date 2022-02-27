package com.example.async;

import com.google.common.util.concurrent.*;
import com.jcabi.aspects.Loggable;
import org.cactoos.func.Async;

import java.util.concurrent.*;


import static com.ea.async.Async.await;

/*
    Create by Atiye Mousavi 
    Date: 2/13/2022
    Time: 3:20 PM
**/
public class JavaAsync {
    static {
//        Electronic Arts ویژگی async-await را از دات نت به اکوسیستم جاوا از طریق کتابخانه ea-async آورد.
//
//این کتابخانه امکان نوشتن کدهای ناهمزمان (غیر مسدود کننده) را به صورت متوالی فراهم می کند. بنابراین، برنامه نویسی ناهمزمان را آسان تر می کند و به طور طبیعی مقیاس می شود.
//
        com.ea.async.Async.init();
    }

    private static final ExecutorService threadpool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int number=20;

        //Thread Example
        factorialUsingThread(number).start();

        //FutureTask Example
        Future<Long> futureTask=factorialUsingFutureTask(number);
        System.out.println("Factorial of " + number + " is:s " + futureTask.get());

        // CompletableFuture Example
        Future<Long> completeFuture=factorialUsingCompletableFuture(number);
        System.out.println("Factorial of " + number + " is " + completeFuture.get());

        // EA Async example
        System.out.println("Factorial of " + number + " is: " + factorialUsingEAAsync(number));

        // cactoos async example
        Future<Long> asyncFuture=factorialUsingCactoos(number);
        System.out.println("Factorial of " + number + " is: " + asyncFuture.get());

        // Guava example
        ListenableFuture<Long> guavaFuture=factorialUsingGuavaServiceSubmit(number);
        System.out.println("Factorial of " + number + " is: " +guavaFuture.get());

        ListenableFuture<Long> guavaFutures=factorialUsingGuavaFutures(number);
        System.out.println("Factorial of " + number + " is: " + guavaFutures.get());

        // @async jcabi-aspect example
        Future<Long> aspectFuture=factorialUsingJcabiAspect(number);
        System.out.println("Factorial of " + number + " is: " + aspectFuture.get());
    }

    public static long factorial(int number) {
        long result = 1;
        for (int i = number; i > 0; i--) {
            result *= i;
        }
        return result;
    }


    @Loggable
    public static Thread factorialUsingThread(int number) {
        Thread newThread = new Thread(() -> {
            System.out.println("Factorial of " + number + " is: " + factorial(number));
        });
        return newThread;
    }


    @Loggable
    public static Future<Long> factorialUsingFutureTask(int number) {
        Future<Long> futureTask=threadpool.submit(() -> factorial(number));
        while(!futureTask.isDone()){
            System.out.println("FutureTask is not finished yet...");
        }
        return futureTask;
    }

    @Loggable
    public static Future<Long> factorialUsingCompletableFuture(int number){
        CompletableFuture<Long> completableFuture=CompletableFuture.supplyAsync(()->factorial(number));
        return completableFuture;
    }

    @Loggable
    public static long factorialUsingEAAsync(int number){
        CompletableFuture<Long> completableFuture=CompletableFuture.supplyAsync(() ->factorial(number));
        long result=await(completableFuture);
        return result;
    }

    @Loggable
    public static Future<Long> factorialUsingCactoos(int number){
        Async<Integer,Long> asyncFunction=new Async<>(input -> factorial(input));
        Future<Long> asyncFuture=asyncFunction.apply(number);
        return asyncFuture;
    }
    @Loggable
    public static ListenableFuture<Long> factorialUsingGuavaServiceSubmit(int number){
//        در اینجا متد submitAsync به آرگومان AsyncCallable نیاز دارد که با استفاده از کلاس Calables ایجاد می‌شود.
//
//علاوه بر این، کلاس Futures متد addCallback را برای ثبت تماس های موفقیت آمیز و شکست ارائه می دهد:
        ListeningExecutorService service= MoreExecutors.listeningDecorator(threadpool);
        ListenableFuture<Long> factorialFuture=service.submit(() -> factorial(number));
        return factorialFuture;
    }
    @Loggable
    public static ListenableFuture<Long> factorialUsingGuavaFutures(int number){
        ListeningExecutorService service=MoreExecutors.listeningDecorator(threadpool);
        AsyncCallable<Long> asyncCallable= Callables.asAsyncCallable(new Callable<Long> (){
            public Long call(){
                return factorial(number);
            }
        },service);
        return Futures.submitAsync(asyncCallable,service);
    }

    @com.jcabi.aspects.Async
    @Loggable
    public static Future<Long> factorialUsingJcabiAspect(int number){
       Future<Long> factorialFuture=CompletableFuture.supplyAsync(()->factorial(number));
       return factorialFuture;
    }
}
