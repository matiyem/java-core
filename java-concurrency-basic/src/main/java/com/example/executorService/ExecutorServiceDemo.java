package com.example.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
    Create by Atiye Mousavi 
    Date: 1/17/2022
    Time: 11:40 AM
**/
public class ExecutorServiceDemo {
//    چارچوب ExecutorService پردازش وظایف در چندین رشته را آسان می کند. ما قصد داریم چند سناریو را مثال بزنیم که در آن منتظر می‌شویم تا thread‌ها اجرای خود را به پایان برسانند.
//
//همچنین، ما نشان خواهیم داد که چگونه می‌توان یک ExecutorService را به‌خوبی خاموش کرد و منتظر ماند تا رشته‌هایی که از قبل در حال اجرا هستند، اجرای خود را به پایان برسانند.

//    هنگام استفاده از Executor، می‌توانیم آن را با فراخوانی متدهای shutdown() یا shutdownNow() خاموش کنیم. اگرچه، تا زمانی که اجرای همه رشته ها متوقف شود، صبر نمی کند.
//
//با استفاده از متد awaitTermination() می توان منتظر ماند تا رشته های موجود اجرا شوند.
//
//این موضوع تا زمانی که همه وظایف اجرای خود را کامل کنند یا به مهلت زمانی مشخص شده برسد مسدود می کند:
    ExecutorService executor= Executors.newFixedThreadPool(10);

    public void execute(){
        executor.submit(()->{
            new Task();
        });
        //        به طور کلی، ExecutorService به طور خودکار از بین نمی رود زمانی که وظیفه ای برای پردازش وجود ندارد. زنده خواهد ماند و منتظر کار جدید خواهد بود.
//
//در برخی موارد این بسیار مفید است، مانند زمانی که یک برنامه نیاز به پردازش وظایفی دارد که به صورت نامنظم ظاهر می شوند یا مقدار کار در زمان کامپایل مشخص نیست.
//
//از طرف دیگر، یک برنامه می تواند به پایان خود برسد اما متوقف نمی شود زیرا یک ExecutorService در انتظار باعث می شود JVM به کار خود ادامه دهد.
//
//برای خاموش کردن صحیح یک ExecutorService، APIهای shutdown() و shutdownNow() را داریم.
//
//متد shutdown() باعث تخریب فوری ExecutorService نمی شود. این باعث می‌شود که ExecutorService پذیرش وظایف جدید را متوقف کند و پس از اتمام کار تمام رشته‌های در حال اجرا، خاموش شود:
        executor.shutdown();
//        متد shutdownNow() سعی می کند بلافاصله ExecutorService را از بین ببرد، اما تضمین نمی کند که تمام رشته های در حال اجرا همزمان متوقف شوند:
//        این روش لیستی از وظایفی را که در انتظار پردازش هستند برمی گرداند. این به توسعه دهنده بستگی دارد که تصمیم بگیرد با این وظایف چه کند.
//
//یکی از راه های خوب برای خاموش کردن ExecutorService (که توسط Oracle نیز توصیه می شود) استفاده از هر دوی این روش ها همراه با متد awaitTermination() است:
        executor.shutdownNow();
        try {
//            با این رویکرد، ExecutorService ابتدا انجام وظایف جدید را متوقف می کند و سپس تا یک بازه زمانی مشخص منتظر می ماند تا همه وظایف تکمیل شوند. اگر این زمان به پایان برسد، اجرا بلافاصله متوقف می شود.
            executor.awaitTermination(20l, TimeUnit.NANOSECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
