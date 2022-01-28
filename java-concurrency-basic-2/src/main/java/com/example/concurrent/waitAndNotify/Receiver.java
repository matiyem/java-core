package com.example.concurrent.waitAndNotify;

import java.util.concurrent.ThreadLocalRandom;

/*
    Create by Atiye Mousavi 
    Date: 1/25/2022
    Time: 11:25 AM
**/
public class Receiver implements Runnable {

    private Data load;

    public Receiver(Data load){
        this.load=load;
    }
    @Override
    public void run() {
//        در اینجا، ما به سادگی load.receive() را در حلقه فراخوانی می کنیم تا زمانی که آخرین بسته داده "End" را دریافت کنیم.
//

        for (String reciveMessage=load.recive(); !"End".equals(reciveMessage); reciveMessage= load.recive()){
            System.out.println(reciveMessage);

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000,5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread Interrupted");
            }
        }

    }
}
