package com.example.concurrent.threadLifeCycle;

/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 4:34 PM
**/
public class NewState implements Runnable{
//    کلاس java.lang.Thread حاوی یک State Enum است که حالت های بالقوه آن را تعریف می کند. در هر نقطه زمانی معین، موضوع فقط می تواند در یکی از این حالت ها باشد:
//
//NEW – یک رشته جدید ایجاد شده که هنوز اجرا را شروع نکرده است
//RUNNABLE – در حال اجرا یا آماده برای اجرا، اما منتظر تخصیص منابع است
//BLOCKED - منتظر دریافت قفل مانیتور برای ورود یا ورود مجدد به بلوک/روش همگام‌سازی شده
//WAITING - انتظار برای انجام یک کار خاص بدون محدودیت زمانی
//TIMED_WAITING - در انتظار انجام یک رشته دیگر برای انجام یک عمل خاص برای یک دوره مشخص
//TERMINATED - اجرای آن را کامل کرده است
    public static void main(String[] args) {

//        یک موضوع جدید (یا یک موضوع متولد شده) موضوعی است که ایجاد شده است اما هنوز شروع نشده است. در این حالت باقی می ماند تا زمانی که آن را با استفاده از متد start() شروع کنیم.
//
//قطعه کد زیر یک رشته جدید ایجاد شده را نشان می دهد که در حالت NEW است:
        Runnable runnable=new NewState();
        Thread t=new Thread(runnable);
        System.out.println(t.getState());//print new
    }
    @Override
    public void run() {

    }
}
