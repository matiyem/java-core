package com.example.exceptions.illegalMonitorState;

/*
    created by Atiye Mousavi
    Date: 3/23/2022
    Time: 8:10 PM
*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SynchronizedReceiver implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(SynchronizedReceiver.class);

    private final Data data;
    private String message;
    private boolean illegalMonitorStateExceptionOccurred;

    public SynchronizedReceiver(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        synchronized (data) {
            try {
                data.wait();
                this.message = data.recive();
            } catch (InterruptedException e) {
                LOG.error("thread was interrupted", e);
                Thread.currentThread().interrupt();
            } catch (IllegalMonitorStateException e) {
                LOG.error("illegal monitor state exception occurred", e);
                illegalMonitorStateExceptionOccurred = true;
            }
        }
    }
    public boolean hasIllegalMonitorStateExceptionOccurred(){
        return illegalMonitorStateExceptionOccurred;
    }

    public String getMessage() {
        return message;
    }
}
