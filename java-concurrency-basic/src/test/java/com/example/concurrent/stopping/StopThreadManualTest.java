package com.example.concurrent.stopping;

import com.example.stopping.ControlSubThread;
import com.jayway.awaitility.Awaitility;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StopThreadManualTest {

    @Test
    public void whenStoppedThreadIsStopped() throws InterruptedException {

        int interval = 5;

        ControlSubThread controlSubThread = new ControlSubThread(interval);
        controlSubThread.start();

        // Give things a chance to get set up
        Thread.sleep(interval);
        assertTrue(controlSubThread.isRunning());
        assertFalse(controlSubThread.isStopped());

        // Stop it and make sure the flags have been reversed
        controlSubThread.stop();
        await().until(() -> assertTrue(controlSubThread.isStopped()));
    }

    @Test
    public void whenInterruptedThreadIsStopped() throws InterruptedException {

        int interval = 50;

        ControlSubThread controlSubThread = new ControlSubThread(interval);
        controlSubThread.start();

        // Give things a chance to get set up
        Thread.sleep(interval);
        assertTrue(controlSubThread.isRunning());
        assertFalse(controlSubThread.isStopped());

        // Stop it and make sure the flags have been reversed
        controlSubThread.interrupt();

        // Wait less than the time we would normally sleep, and make sure we exited.
//        این تست با موفقیت اجرا نمیشود اگر 5 میلی ثانیه صبر میکند تا شرایط isStoping محقق شود در غیر این صورت یک exception بر میگرداند
        Awaitility.await()
                .pollDelay(2, TimeUnit.MILLISECONDS)
                .atMost(interval/ 10, TimeUnit.MILLISECONDS)
                .until(controlSubThread::isStopped);
    }
}
