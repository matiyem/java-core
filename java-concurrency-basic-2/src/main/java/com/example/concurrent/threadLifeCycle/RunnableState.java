package com.example.concurrent.threadLifeCycle;

/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 4:36 PM
**/
public class RunnableState implements Runnable {
    public static void main(String[] args) {
//        هنگامی که یک رشته جدید ایجاد می کنیم و متد start() را روی آن فراخوانی می کنیم، از حالت NEW به حالت RUNNABLE منتقل می شود. موضوعات در این حالت یا در حال اجرا هستند یا آماده اجرا هستند، اما منتظر تخصیص منابع از سیستم هستند.
//
//در یک محیط چند رشته ای، Thread-Scheduler (که بخشی از JVM است) زمان ثابتی را به هر رشته اختصاص می دهد. بنابراین برای مدت زمان خاصی اجرا می شود، سپس کنترل را به رشته های RUNNABLE دیگر واگذار می کند.
//توجه داشته باشید که در این مثال، همیشه تضمین نمی‌شود که تا زمانی که کنترل ما به t.getState() برسد، همچنان در حالت RUNNABLE باشد.
//
//ممکن است این اتفاق بیفتد که بلافاصله توسط Thread-Scheduler برنامه ریزی شده باشد و ممکن است اجرا به پایان برسد. در چنین مواردی ممکن است خروجی متفاوتی دریافت کنیم.
        Runnable runnable=new NewState();
        Thread t=new Thread(runnable);
        t.start();
        System.out.println(t.getState());//print runnable
    }
    @Override
    public void run() {

    }
}
