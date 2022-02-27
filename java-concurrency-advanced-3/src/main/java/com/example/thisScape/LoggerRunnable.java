package com.example.thisScape;

/*
    Create by Atiye Mousavi 
    Date: 2/16/2022
    Time: 10:26 AM
**/
public class LoggerRunnable implements Runnable {
//    در اینجا، ما به صراحت این مرجع را به سازنده Thread منتقل می کنیم. بنابراین، موضوع تازه شروع شده ممکن است بتواند شی محصور را قبل از اینکه ساخت کامل آن کامل شود، ببیند. در زمینه های همزمان، این ممکن است باعث ایجاد اشکالات ظریف شود.
//
//همچنین می توان به طور ضمنی از این مرجع عبور کرد:
    public LoggerRunnable() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("started...");
    }
}
