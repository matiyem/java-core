package com.example.concurrent.interrupt;

/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 12:02 PM
**/
public class InterruptExample extends Thread{
//    هدف سیستم وقفه ارائه یک چارچوب کاملاً تعریف شده برای اجازه دادن به رشته‌ها برای قطع کردن وظایف (بالقوه زمان‌بر) در رشته‌های دیگر است. یک راه خوب برای فکر کردن در مورد وقفه این است که در واقع یک رشته در حال اجرا را قطع نمی کند - فقط درخواست می کند که موضوع در فرصت مناسب بعدی خود را قطع کند.
//Thread ها ممکن است به دلایل مختلفی مسدود شوند: انتظار برای بیدار شدن از یک Thread.sleep()، انتظار برای به دست آوردن قفل، انتظار برای تکمیل I/O، یا انتظار برای نتیجه محاسبه در یک رشته دیگر، و غیره.
//
//InterruptedException معمولاً با همه روش‌های مسدود کردن پرتاب می‌شود تا بتوان آن را مدیریت کرد و اقدام اصلاحی را انجام داد. چندین روش در جاوا وجود دارد که InterruptedException را پرتاب می کند. اینها عبارتند از Thread.sleep()، Thread.join()، متد wait() از کلاس Object و متدهای put() و take() BlockingQueue.
//    Thread متد interrupt() را برای قطع کردن یک رشته ارائه می‌کند، و برای پرس و جو که آیا یک رشته قطع شده است، می‌توانیم از متد isInterrupted() استفاده کنیم. گاهی اوقات، ممکن است بخواهیم آزمایش کنیم که آیا موضوع فعلی قطع شده است یا خیر و اگر چنین است، فوراً این استثنا را حذف کنیم. در اینجا می توانیم از متد interrupted() استفاده کنیم:
//    مکانیسم وقفه با استفاده از پرچمی به نام وضعیت وقفه اجرا می شود. هر رشته دارای یک ویژگی بولی است که نشان دهنده وضعیت قطع شده آن است. فراخوانی () Thread.interrupt این پرچم را تنظیم می کند. هنگامی که یک رشته با فراخوانی متد استاتیک (Thread.interrupted) وجود وقفه را بررسی می کند، وضعیت وقفه پاک می شود.
//
    public static void propagateException() throws InterruptedException {
        Thread.sleep(1000);
        Thread.currentThread().interrupt();
        boolean flag=Thread.interrupted();
        if (flag){
            throw new InterruptedException();
        }
    }
    public static Boolean restoreTheState(){
        InterruptExample thread1=new InterruptExample();
        thread1.start();
        thread1.interrupt();
        return thread1.isInterrupted();
    }
    public void run(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public static void throwCustomException() throws InterruptedException, CustomInterruptedException {
        Thread.sleep(1000);
        Thread.currentThread().interrupt();
        if (Thread.interrupted()){
            throw new CustomInterruptedException("This thread was interrupted");
        }
    }
    public static Boolean handleWithCustomException() throws CustomInterruptedException {
        try {
            Thread.sleep(1000);
            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CustomInterruptedException("This thread was interrupt...");
        }
        return Thread.currentThread().isInterrupted();
    }
}
