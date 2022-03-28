package com.example.exceptions.illegalMonitorState;

/*
    created by Atiye Mousavi
    Date: 3/23/2022
    Time: 8:46 PM
*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnsynchronizedSender implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(UnsynchronizedSender.class);

    private final Data data;
    private boolean illegalMonitorStateExceptionOccurred;

    public UnsynchronizedSender(Data data){
        this.data=data;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            data.send("test");
            data.notifyAll();
        } catch (InterruptedException e) {
            LOG.error("thread was interrupted", e);
            Thread.currentThread().interrupt();
        } catch (IllegalMonitorStateException e) {
            LOG.error("illegal monitor state exception occurred", e);
            illegalMonitorStateExceptionOccurred = true;
        }
    }
    public boolean hasIllegalMonitorStateExceptionOccurred() {
        return illegalMonitorStateExceptionOccurred;
    }

}
