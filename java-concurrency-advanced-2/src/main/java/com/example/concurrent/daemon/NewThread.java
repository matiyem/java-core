package com.example.concurrent.daemon;

/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 12:13 PM
 **/


public class NewThread extends Thread {
    public void run() {
        long startTime = System.currentTimeMillis();
        while (true) {
            for (int i = 0; i < 10; i++) {
                System.out.println(this.getName() + ": New Thread is runing..." + i);

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
