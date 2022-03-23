package com.example.globalExceptionHandler;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 3:32 PM
*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChildThread extends Thread {
    private static Logger LOGGER= LoggerFactory.getLogger(ChildThread.class);

    public void run(){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException caught!");
        }
    }
}
class InterruptedExceptionExample{
    public static void main(String[] args) {
        ChildThread childThread=new ChildThread();
        childThread.start();
        childThread.interrupt();
    }

}

