package com.example.concurrent.waitAndNotify;

/*
    Create by Atiye Mousavi 
    Date: 1/25/2022
    Time: 11:09 AM
**/
public class Data {
    private String packet;

    //True if reciver should wait
    //False if sender should wait
    private boolean transfer = true;

    public synchronized String recive(){
        while (transfer){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread Interrupted");
            }
        }
        transfer=true;

        String returnPacket=packet;
//        متد wait() باعث می‌شود تا رشته فعلی به‌طور نامحدود منتظر بماند تا رشته‌ای دیگر یا notify() را برای این شیء فراخوانی کند یا notifyAll().
//        این روش به سادگی تمام رشته هایی را که در مانیتور این شی منتظر هستند بیدار می کند.
//
//رشته های بیدار شده مانند هر رشته دیگری به روش معمول تکمیل می شوند.
//
//اما قبل از اینکه اجازه دهیم اجرای آنها ادامه یابد، همیشه یک بررسی سریع برای شرایط مورد نیاز برای ادامه کار تعریف کنید. این به این دلیل است که ممکن است شرایطی وجود داشته باشد که رشته بدون دریافت اعلان از خواب بیدار شود (این سناریو بعداً در یک مثال مورد بحث قرار خواهد گرفت).
        notifyAll();
        return returnPacket;
    }
    public synchronized void send(String packet){
        while (!transfer){
            try {
//                از آنجایی که notify() و notifyAll() به‌طور تصادفی رشته‌هایی را که در مانیتور این شی منتظر هستند بیدار می‌کنند، همیشه مهم نیست که این شرط برآورده شود. گاهی اوقات نخ بیدار می شود، اما شرایط در واقع هنوز برآورده نشده است.
//
//همچنین می‌توانیم چکی را برای نجات از بیدار شدن‌های جعلی تعریف کنیم - جایی که یک رشته می‌تواند بدون دریافت اعلان از انتظار بیدار شود.
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread Interrupted");
            }
        }
        transfer=false;
        this.packet=packet;
        notifyAll();
    }
}
