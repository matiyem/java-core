package com.example.synchronize;

import com.example.concurrent.synchronize.BaeldungSynchronizedBlocks;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class BaeldungSychronizedBlockUnitTest {

    @Test
    public void givenMultiThread_whenBlockSync() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        BaeldungSynchronizedBlocks synchronizedBlocks = new BaeldungSynchronizedBlocks();

        IntStream.range(0, 1000)
          .forEach(count -> service.submit(synchronizedBlocks::performSynchronisedTask));
        service.awaitTermination(500, TimeUnit.MILLISECONDS);

        assertEquals(1000, synchronizedBlocks.getCount());
    }

    @Test
    public void givenMultiThread_whenStaticSyncBlock() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        IntStream.range(0, 1000)
          .forEach(count -> service.submit(BaeldungSynchronizedBlocks::performStaticSyncTask));
        service.awaitTermination(500, TimeUnit.MILLISECONDS);

        assertEquals(1000, BaeldungSynchronizedBlocks.getStaticCount());
    }

    @Test
    public void givenHoldingTheLock_whenReentrant_thenCanAcquireItAgain() {
//        قفل پشت روش ها و بلوک های هماهنگ شده مجددا وارد می شود. این بدان معناست که رشته فعلی می‌تواند همان قفل همگام‌سازی شده را در حالی که آن را نگه داشته است، بارها و بارها به دست آورد:
//        همانطور که در زیر نشان داده شده است، در حالی که ما در یک بلوک همگام هستیم، می توانیم قفل مانیتور یکسان را به طور مکرر بدست آوریم.
        Object lock = new Object();
        synchronized (lock) {
            System.out.println("First time acquiring it");

            synchronized (lock) {
                System.out.println("Entering again");

                synchronized (lock) {
                    System.out.println("And again");
                }
            }
        }
    }

}
