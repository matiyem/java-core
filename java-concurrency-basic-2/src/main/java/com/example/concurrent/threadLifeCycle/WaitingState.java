package com.example.concurrent.threadLifeCycle;

/*
    Create by Atiye Mousavi 
    Date: 1/25/2022
    Time: 10:14 AM
**/
public class WaitingState implements Runnable {

    public static Thread t1;

    public static void main(String[] args) {
//        یک رشته زمانی در حالت WAITING است که منتظر رشته دیگری برای انجام یک عمل خاص است. طبق JavaDocs، هر رشته ای می تواند با فراخوانی یکی از سه روش زیر وارد این حالت شود:
//
//object.wait()
//thread.join() یا
//LockSupport.park()
//توجه داشته باشید که در wait() و join() - ما هیچ دوره زمانی تعریف نمی کنیم زیرا این سناریو در بخش بعدی پوشش داده شده است.
//        بیایید در مورد آنچه در اینجا انجام می دهیم بحث کنیم:
//
//ما t1 را ایجاد و شروع کردیم
//t1 یک t2 ایجاد می کند و آن را شروع می کند
//در حالی که پردازش t2 ادامه دارد، t2.join() را فراخوانی می کنیم، این کار t1 را در حالت WAITING قرار می دهد تا زمانی که اجرای t2 به پایان برسد.
//از آنجایی که t1 منتظر تکمیل t2 است، ما از t2 ()t1.getState را فراخوانی می کنیم.
//خروجی در اینجا همانطور که انتظار دارید این است:
        t1 = new Thread(new WaitingState());
        t1.start();

    }

    @Override
    public void run() {
        Thread t2 = new Thread(new DemoThreadWS());
        t2.start();

        try {
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}

class DemoThreadWS implements Runnable {

    @Override
    public void run() {
//        تمرین خوبی است که روش خواب را در یک بلوک try/catch بپیچید تا در صورتی که نخ دیگری رشته خواب را قطع کند. در این حالت، InterruptedException را می‌گیریم و به صراحت رشته فعلی را قطع می‌کنیم، بنابراین می‌توان بعداً آن را گرفت و مدیریت کرد. این در برنامه‌های چند رشته‌ای مهم‌تر است، اما در صورتی که بعداً رشته‌های دیگری را اضافه کنیم، در برنامه‌های تک رشته‌ای نیز تمرین خوبی است.
        try {
//            یک راه سریع و کثیف برای مکث در جاوا این است که به رشته فعلی بگویید برای مدت زمان مشخصی بخوابد. این را می توان با استفاده از Thread.sleep (میلی ثانیه) انجام داد:
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        System.out.println(WaitingState.t1.getState());//print WAITING

    }
}
