package com.example.concurrent.locks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;


/*
    Create by Atiye Mousavi 
    Date: 1/31/2022
    Time: 11:46 AM
**/
public class SharedObjectWithLock {
    //    علاوه بر رابط Lock، ما یک رابط ReadWriteLock داریم که دارای یک جفت قفل است، یکی برای عملیات فقط خواندنی و دیگری برای عملیات نوشتن. قفل خواندن ممکن است به طور همزمان توسط چندین رشته نگه داشته شود تا زمانی که نوشتن وجود نداشته باشد.
//
//ReadWriteLock روش هایی را برای به دست آوردن قفل خواندن یا نوشتن اعلام می کند:
//
//Lock readLock() – قفلی را که برای خواندن استفاده می شود برمی گرداند
//Lock writeLock() – قفلی را که برای نوشتن استفاده می شود برمی گرداند
    private static final Logger LOG = LoggerFactory.getLogger(SharedObjectWithLock.class);

    private ReentrantLock lock = new ReentrantLock(true);

    private int counter = 0;

    void perform() {
        lock.lock();
        LOG.info("Thread - " + Thread.currentThread().getName() + " acquired the lock");
        try {

            LOG.info("Thread - " + Thread.currentThread().getName() + " processing");
            counter++;
        } catch (Exception exception) {
            LOG.error(" Interrupted Exception ", exception);
        } finally {
            lock.unlock();
            LOG.info("Thread - " + Thread.currentThread().getName() + " released the lock");
        }
    }

    private void performTryLock() {
        LOG.info("Thread - " + Thread.currentThread().getName() + " attempting to acuire the lock");
        try {
            boolean isLockAcquired = lock.tryLock(2, TimeUnit.SECONDS);
            if (isLockAcquired) {
                try {

                    LOG.info("Thread - " + Thread.currentThread().getName() + " acquired the lock");

                    LOG.info("Thread - " + Thread.currentThread().getName() + " processing");
                    sleep(1000);

                } finally {
                    lock.unlock();
                    LOG.info("Thread - " + Thread.currentThread().getName() + " release the lock");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info("Thread - " + Thread.currentThread().getName() + " could not acquire the lock");
    }

    public ReentrantLock getLock() {
        return lock;
    }

    boolean isLocked() {
        return lock.isLocked();
    }
    boolean hasQueuedThreads(){
        return lock.hasQueuedThreads();
    }
    int getCounter(){
        return counter;
    }

    public static void main(String[] args) {
        final int threadCount=2;
        final ExecutorService service= Executors.newFixedThreadPool(threadCount);
        final SharedObjectWithLock object=new SharedObjectWithLock();

        service.execute(object::perform);
        service.execute(object::performTryLock);

        service.shutdown();
    }
}
