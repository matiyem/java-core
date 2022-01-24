package com.example.ScheduledExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 10:22 AM
**/
public class ScheduledExecutorServiceDemo {
//    کد بالا یک کار را پس از 100 میلی ثانیه تاخیر اولیه اجرا می کند و پس از آن، همان کار را هر 450 میلی ثانیه انجام می دهد.
//
//اگر پردازنده نتواند پردازش کار را به موقع قبل از وقوع بعدی به پایان برساند، ScheduledExecutorService قبل از شروع کار بعدی منتظر می ماند تا کار فعلی تکمیل شود.
//
//برای جلوگیری از این زمان انتظار، می‌توانیم از scheduleWithFixedDelay() استفاده کنیم که همانطور که در نامش توضیح داده شده است، تاخیر ثابتی را بین تکرارهای کار تضمین می‌کند.
//
//برای جزئیات بیشتر درباره ScheduledExecutorService، لطفاً راهنمای ما برای Java ExecutorService را بخوانید.




//    پس از انتشار جاوا 7، بسیاری از توسعه دهندگان تصمیم گرفتند تا چارچوب ExecutorService را با فریم ورک fork/join جایگزین کنند.
//
//با این حال این همیشه تصمیم درستی نیست. با وجود سادگی و افزایش عملکرد مکرر مرتبط با فورک/پیوستن، کنترل توسعه‌دهنده بر اجرای همزمان را کاهش می‌دهد.
//
//ExecutorService به توسعه دهنده این امکان را می دهد که تعداد رشته های تولید شده و دانه بندی وظایفی که باید توسط رشته های جداگانه اجرا شوند را کنترل کند. بهترین مورد استفاده برای ExecutorService پردازش وظایف مستقل مانند تراکنش ها یا درخواست ها بر اساس طرح "یک رشته برای یک کار" است.
//
//در مقابل، طبق مستندات اوراکل، فورک/پیوستن برای سرعت بخشیدن به کار طراحی شده است که می تواند به صورت بازگشتی به قطعات کوچکتر تقسیم شود.

    private void execute() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        getTasksToRun().apply(executorService);
//        به طور کلی، ExecutorService به طور خودکار از بین نمی رود زمانی که وظیفه ای برای پردازش وجود ندارد. زنده خواهد ماند و منتظر کار جدید خواهد بود.
//
//در برخی موارد این بسیار مفید است، مانند زمانی که یک برنامه نیاز به پردازش وظایفی دارد که به صورت نامنظم ظاهر می شوند یا مقدار کار در زمان کامپایل مشخص نیست.
//
//از طرف دیگر، یک برنامه می تواند به پایان خود برسد اما متوقف نمی شود زیرا یک ExecutorService در انتظار باعث می شود JVM به کار خود ادامه دهد.
//
//برای خاموش کردن صحیح یک ExecutorService، APIهای shutdown() و shutdownNow() را داریم.
//
//متد shutdown() باعث تخریب فوری ExecutorService نمی شود. این باعث می‌شود که ExecutorService پذیرش وظایف جدید را متوقف کند و پس از اتمام کار تمام رشته‌های در حال اجرا، خاموش شود:
        executorService.shutdown();
    }

    private void executeWithMultiThread() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        getTasksToRun().apply(executorService);
        executorService.shutdown();
    }

//ScheduledExecutorService یک رابط مشابه با ExecutorService است، اما می تواند وظایف را به صورت دوره ای انجام دهد.
//
//روش های Executor و ExecutorService در محل بدون ایجاد تاخیر مصنوعی برنامه ریزی می شوند. صفر یا هر مقدار منفی نشان می دهد که درخواست باید فوراً اجرا شود.
//
//ما می توانیم از هر دو رابط Runnable و Calable برای تعریف وظیفه استفاده کنیم.
    private Function<ScheduledExecutorService, Void> getTasksToRun() {
//        در اینجا، متد scheduleAtFixedRate (فرمان قابل اجرا، تاخیر اولیه طولانی، دوره طولانی، واحد TimeUnit) یک عمل دوره ای ایجاد و اجرا می کند که ابتدا پس از تاخیر اولیه ارائه شده، و متعاقباً با دوره داده شده تا زمان خاموش شدن نمونه سرویس فراخوانی می شود.
//
//متد scheduleWithFixedDelay (فرمان قابل اجرا، تاخیر اولیه طولانی، تاخیر طولانی، واحد TimeUnit) یک عمل دوره ای را ایجاد و اجرا می کند که ابتدا پس از تاخیر اولیه ارائه شده، و مکررا با تاخیر داده شده بین خاتمه عملیات اجرا و فراخوانی فراخوانی می شود. بعدی.




//طبق قراردادهای متد scheduleAtFixedRate() و scheduleWithFixedDelay()، اجرای دوره کار با خاتمه ExecutorService یا در صورت ایجاد استثنا در طول اجرای کار به پایان می رسد.
        return (executorService -> {
//            ScheduledExecutorService همچنین می تواند کار را پس از مدتی تاخیر ثابت زمان بندی کند:
            ScheduledFuture<?> scheduledFuture1 = executorService.schedule(() -> {
                //Task
            }, 1, TimeUnit.SECONDS);
//            متد scheduleAtFixedRate() به ما اجازه می دهد تا یک کار را به صورت دوره ای پس از یک تاخیر ثابت اجرا کنیم. کد بالا قبل از اجرای callableTask یک ثانیه تاخیر دارد.
//
//بلوک کد زیر یک کار را پس از 100 میلی ثانیه تاخیر اولیه اجرا می کند. و پس از آن، همان کار را هر 10 میلی ثانیه اجرا می کند:
            ScheduledFuture<?> scheduledFuture2 = executorService.scheduleAtFixedRate(() -> {
                //Task
            }, 1, 10, TimeUnit.SECONDS);
//            اگر پردازنده نسبت به پارامتر period متد ()scheduledAtFixedRate برای اجرای یک کار اختصاص داده شده به زمان بیشتری نیاز داشته باشد، ScheduledExecutorService قبل از شروع کار بعدی منتظر می ماند تا کار فعلی تکمیل شود.
//
//اگر لازم است یک تاخیر طول ثابت بین تکرارهای کار وجود داشته باشد، باید از scheduleWithFixedDelay() استفاده شود.
//
//به عنوان مثال، کد زیر یک مکث 10  ثانیه ای بین پایان اجرای فعلی و شروع اجرای دیگری را تضمین می کند:
            ScheduledFuture<?> scheduledFuture3 = executorService.scheduleWithFixedDelay(() -> {
                //Task
            }, 1, 10, TimeUnit.SECONDS);
            ScheduledFuture<?> scheduledFuture4 = executorService.schedule(() -> {
                //Task
                return "Hellow world";
            }, 1, TimeUnit.SECONDS);

            return null;
        });
    }

    public static void main(String[] args) {
        ScheduledExecutorServiceDemo demo=new ScheduledExecutorServiceDemo();
        demo.execute();
        demo.executeWithMultiThread();
    }
}
