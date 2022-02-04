package com.example.concurrent.countDownLatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/*
    Create by Atiye Mousavi 
    Date: 1/30/2022
    Time: 3:46 PM
**/
public class WaitingWorker implements Runnable{

    private final List<String> outputScraper;
    private final CountDownLatch readyThreadCounter;
    private final CountDownLatch callingThreadBlocker;
    private final CountDownLatch completedThreadCounter;

    public WaitingWorker(final List<String> outputScraper,final CountDownLatch readyThreadCounter,final CountDownLatch callingThreadBlocker,final CountDownLatch completedThreadCounter) {
        this.outputScraper = outputScraper;
        this.readyThreadCounter = readyThreadCounter;
        this.callingThreadBlocker = callingThreadBlocker;
        this.completedThreadCounter = completedThreadCounter;
    }

    @Override
    public void run() {
        readyThreadCounter.countDown();

        try {
            callingThreadBlocker.await();
            outputScraper.add("Counted down");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            completedThreadCounter.countDown();
        }

    }
}
