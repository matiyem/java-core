package com.example.concurrent.daemon;

/*
    Create by Atiye Mousavi 
    Date: 1/31/2022
    Time: 9:44 AM
**/
public class NewThread extends Thread {
    public void run() {
        long startTime=System.currentTimeMillis();
        while (true) {
            for (int i = 0; i < 10; i++) {
                System.out.println(this.getName() + ": New Thread is running..." + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (System.currentTimeMillis() - startTime >2000){
                Thread.currentThread().interrupt();
                break;
            }
        }

    }
}
