package com.example.concurrent.locks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

import static java.lang.Thread.sleep;

/*
    Create by Atiye Mousavi 
    Date: 1/31/2022
    Time: 12:28 PM
**/
public class StampedLockDemo {
//    StampedLock در جاوا 8 معرفی شده است. همچنین از قفل خواندن و نوشتن پشتیبانی می کند. با این حال، روش‌های کسب قفل مهری را برمی‌گردانند که برای آزاد کردن قفل یا بررسی اینکه آیا قفل هنوز معتبر است یا خیر، استفاده می‌شود:
//    یکی دیگر از ویژگی های ارائه شده توسط StampedLock قفل خوش بینانه است. اکثر اوقات عملیات خواندن نیازی به منتظر ماندن برای تکمیل عملیات نوشتن ندارند و در نتیجه به قفل خواندن کامل نیازی نیست.
//
//در عوض، می‌توانیم برای خواندن قفل ارتقا دهیم:

    private Map<String, String> map = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(StampedLockDemo.class);

    private final StampedLock lock = new StampedLock();

    public void put(String key, String value) {
        long stamp = lock.writeLock();
        try {

            logger.info(Thread.currentThread().getName() + " acquired the write lock with stamp " + stamp);
            map.put(key, value);
        } finally {
            lock.unlockWrite(stamp);
            logger.info(Thread.currentThread().getName() + " unlocked the write lock with stamp " + stamp);
        }
    }

    public String get(String key) throws InterruptedException {
        long stamp = lock.readLock();
        logger.info(Thread.currentThread().getName() + " acquired the read lock with stamp " + stamp);
        try {
            sleep(5000);
            return map.get(key);
        } finally {
            lock.unlockRead(stamp);
            logger.info(Thread.currentThread().getName() + " unlocked the read lock with stamp " + stamp);
        }
    }

    private String readWithOptimistickLock(String key) throws InterruptedException {
        long stamp = lock.tryOptimisticRead();
        String value = map.get(key);

        if (!lock.validate(stamp)) {
            stamp = lock.readLock();

            try {
                sleep(5000);
                return map.get(key);
            } finally {
                lock.unlock(stamp);
                logger.info(Thread.currentThread().getName() + " unlocked the read lock with stamp " + stamp);
            }
        }
        return value;
    }

    public static void main(String[] args) {
        final int threadCount = 4;
        final ExecutorService service = Executors.newFixedThreadPool(threadCount);
        StampedLockDemo object = new StampedLockDemo();

        Runnable writeTask = () -> {
            object.put("key1", "value1");
        };
        Runnable readTask = () -> {
            try {
                object.get("key1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable readOptimisticTask = () -> {
            try {
                object.readWithOptimistickLock("key1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        service.submit(writeTask);
        service.submit(writeTask);
        service.submit(readTask);
        service.submit(readOptimisticTask);
        service.shutdown();
    }
}
