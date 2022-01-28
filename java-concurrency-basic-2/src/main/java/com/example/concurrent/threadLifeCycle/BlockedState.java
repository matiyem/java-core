package com.example.concurrent.threadLifeCycle;

/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 4:30 PM
**/
public class BlockedState {
    public static void main(String[] args) throws InterruptedException {
//        یک رشته زمانی که در حال حاضر واجد شرایط اجرا نیست، در حالت BLOCKED است. زمانی وارد این حالت می شود که منتظر قفل مانیتور است و سعی می کند به بخشی از کد که توسط رشته دیگری قفل شده است دسترسی پیدا کند.
//
//بیایید سعی کنیم این حالت را بازتولید کنیم:
//        در این کد:
//
//ما دو موضوع مختلف ایجاد کرده‌ایم - t1 و t2
//t1 شروع می شود و متد ()commonResource همگام سازی شده را وارد می کند. این بدان معنی است که فقط یک رشته می تواند به آن دسترسی داشته باشد. تمام رشته‌های بعدی که سعی در دسترسی به این روش دارند، از اجرای بعدی مسدود می‌شوند تا زمانی که مورد فعلی پردازش را به پایان برساند.
//وقتی t1 وارد این روش می شود، در یک حلقه while بی نهایت نگه داشته می شود. این فقط برای تقلید از پردازش های سنگین است به طوری که همه رشته های دیگر نتوانند وارد این روش شوند
//اکنون وقتی t2 را شروع می کنیم، سعی می کند متد ()commonResource را وارد کند که قبلاً توسط t1 در دسترس است، بنابراین، t2 در حالت BLOCKED نگه داشته می شود.
//در این حالت، t2.getState() را فراخوانی می کنیم و خروجی را به صورت زیر دریافت می کنیم:
        Thread t1=new Thread(new DemoThreadB());
        Thread t2=new Thread(new DemoThreadB());

        t1.start();
        t2.start();

        Thread.sleep(1000);
        System.out.println(t2.getState());//print BLOCKED
        System.exit(0);
    }
}
class DemoThreadB implements Runnable{

    @Override
    public void run() {
        commonResource();

    }
    public static synchronized void commonResource(){
        while(true){
            // Infinite loop to mimic heavy processing
            // Thread 't1' won't leave this method
            // when Thread 't2' enters this
        }

    }
}
