package com.example.concurrent.countDownLatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/*
    Create by Atiye Mousavi 
    Date: 1/30/2022
    Time: 3:42 PM
**/
public class BrokenWorker implements Runnable {
//    گاهی اوقات، ممکن است با موقعیتی مواجه شویم که Workers قبل از شمارش معکوس CountDownLatch به اشتباه به پایان می رسد. این می تواند باعث شود که هرگز به صفر نرسد و await() هرگز خاتمه نیابد:
    private final List<String> outputScraper;
    private final CountDownLatch countDownLatch;

    public BrokenWorker(final List<String> outputScraper,final CountDownLatch countDownLatch) {
        this.outputScraper = outputScraper;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        if (true){
            throw new RuntimeException("Oh dear");
        }
        countDownLatch.countDown();
        outputScraper.add("Counted down");

    }
}
