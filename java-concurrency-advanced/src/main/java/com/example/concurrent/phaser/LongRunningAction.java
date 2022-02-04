package com.example.concurrent.phaser;

import java.util.concurrent.Phaser;

/*
    Create by Atiye Mousavi 
    Date: 2/1/2022
    Time: 9:12 AM
**/
public class LongRunningAction implements Runnable{
//    بیایید بگوییم که می خواهیم چندین مرحله از اقدامات را هماهنگ کنیم. سه thread فاز اول را پردازش خواهند کرد و دو نخ نیز فاز دوم را پردازش خواهند کرد.
//
//ما یک کلاس LongRunningAction ایجاد می کنیم که رابط Runnable را پیاده سازی می کند:



    private String threadName;
    private Phaser ph;

    LongRunningAction(String threadName,Phaser ph){
        this.threadName=threadName;
        this.ph=ph;
        ph.register();
    }

    @Override
    public void run() {
        System.out.println("This is phase " + ph.getPhase());
        System.out.println("Thread " + threadName + " befor long running action");
        ph.arriveAndAwaitAdvance();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ph.arriveAndDeregister();
    }
}
