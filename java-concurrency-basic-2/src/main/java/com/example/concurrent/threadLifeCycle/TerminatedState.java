package com.example.concurrent.threadLifeCycle;

/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 4:37 PM
**/
public class TerminatedState implements Runnable{
    public static void main(String[] args) throws InterruptedException {
//        این حالت یک نخ مرده است. زمانی در حالت TERMINATED قرار می گیرد که یا اجرای آن به پایان رسیده یا به طور غیرعادی خاتمه یافته است.
//
//ما یک مقاله اختصاصی داریم که روش های مختلف توقف موضوع را مورد بحث قرار می دهد.
//این حالت یک نخ مرده است. زمانی در حالت TERMINATED قرار می گیرد که یا اجرای آن به پایان رسیده یا به طور غیرعادی خاتمه یافته است.
//
//ما یک مقاله اختصاصی داریم که روش های مختلف توقف موضوع را مورد بحث قرار می دهد.
//
//بیایید سعی کنیم در مثال زیر به این حالت برسیم:
        Thread t1=new Thread(new TerminatedState());
        t1.start();
        Thread.sleep(1000);
        System.out.println(t1.getState());//print terminate
    }
    @Override
    public void run() {

    }
}
