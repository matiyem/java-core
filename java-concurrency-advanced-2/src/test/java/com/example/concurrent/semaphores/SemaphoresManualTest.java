package com.example.concurrent.semaphores;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SemaphoresManualTest {
//    توجه داشته باشید که چگونه از روش های زیر استفاده کردیم:
//
//tryAcquire() - اگر مجوز فوراً در دسترس باشد، true را برگردانید و آن را بدست آورید در غیر این صورت false را دریافت کنید، اما ()acquire یک مجوز دریافت می کند و تا زمانی که مجوز در دسترس باشد مسدود می کند.
//release() – مجوز را آزاد کنید
//availablePermits() – تعداد مجوزهای موجود را برمی گرداند
//برای آزمایش صف ورود خود، ابتدا سعی می کنیم به حد مجاز برسیم و بررسی کنیم که آیا تلاش بعدی برای ورود مسدود می شود یا خیر:
    // ========= login queue ======

    @Test
    public void givenLoginQueue_whenReachLimit_thenBlocked() throws InterruptedException {
        final int slots = 10;
        final ExecutorService executorService = Executors.newFixedThreadPool(slots);
        final LoginQueueUsingSemaphore loginQueue = new LoginQueueUsingSemaphore(slots);
        IntStream.range(0, slots)
          .forEach(user -> executorService.execute(loginQueue::tryLogin));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        assertEquals(0, loginQueue.availableSlots());
        assertFalse(loginQueue.tryLogin());
    }

    @Test
    public void givenLoginQueue_whenLogout_thenSlotsAvailable() throws InterruptedException {
        final int slots = 10;
        final ExecutorService executorService = Executors.newFixedThreadPool(slots);
        final LoginQueueUsingSemaphore loginQueue = new LoginQueueUsingSemaphore(slots);
        IntStream.range(0, slots)
          .forEach(user -> executorService.execute(loginQueue::tryLogin));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        assertEquals(0, loginQueue.availableSlots());
        loginQueue.logout();
        assertTrue(loginQueue.availableSlots() > 0);
        assertTrue(loginQueue.tryLogin());
    }

    // ========= delay queue =======

    @Test
    public void givenDelayQueue_whenReachLimit_thenBlocked() throws InterruptedException {
//        اما پس از مدتی خوابیدن، سمافور باید مجوزها را بازنشانی کرده و آزاد کند:
        final int slots = 50;
        final ExecutorService executorService = Executors.newFixedThreadPool(slots);
        final DelayQueueUsingTimedSemaphore delayQueue = new DelayQueueUsingTimedSemaphore(1, slots);
        IntStream.range(0, slots)
          .forEach(user -> executorService.execute(delayQueue::tryAdd));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        assertEquals(0, delayQueue.availableSlots());
        assertFalse(delayQueue.tryAdd());
    }

    @Test
    public void givenDelayQueue_whenTimePass_thenSlotsAvailable() throws InterruptedException {
        final int slots = 50;
        final ExecutorService executorService = Executors.newFixedThreadPool(slots);
        final DelayQueueUsingTimedSemaphore delayQueue = new DelayQueueUsingTimedSemaphore(1, slots);
        IntStream.range(0, slots)
          .forEach(user -> executorService.execute(delayQueue::tryAdd));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        assertEquals(0, delayQueue.availableSlots());
        Thread.sleep(1000);
        assertTrue(delayQueue.availableSlots() > 0);
        assertTrue(delayQueue.tryAdd());
    }

    // ========== mutex ========

    @Test
    public void whenMutexAndMultipleThreads_thenBlocked() throws InterruptedException {
        final int count = 5;
        final ExecutorService executorService = Executors.newFixedThreadPool(count);
        final CounterUsingMutex counter = new CounterUsingMutex();
        IntStream.range(0, count)
          .forEach(user -> executorService.execute(() -> {
              try {
                  counter.increase();
              } catch (final InterruptedException e) {
                  e.printStackTrace();
              }
          }));
        executorService.shutdown();

        assertTrue(counter.hasQueuedThreads());
    }

    @Test
    public void givenMutexAndMultipleThreads_ThenDelay_thenCorrectCount() throws InterruptedException {
        final int count = 5;
        final ExecutorService executorService = Executors.newFixedThreadPool(count);
        final CounterUsingMutex counter = new CounterUsingMutex();
        IntStream.range(0, count)
          .forEach(user -> executorService.execute(() -> {
              try {
                  counter.increase();
              } catch (final InterruptedException e) {
                  e.printStackTrace();
              }
          }));
        executorService.shutdown();
        assertTrue(counter.hasQueuedThreads());
        Thread.sleep(5000);
        assertFalse(counter.hasQueuedThreads());
        assertEquals(count, counter.getCount());
    }
}
