package com.example.concurrent.countdownlatch;

import com.example.concurrent.countDownLatch.BrokenWorker;
import com.example.concurrent.countDownLatch.WaitingWorker;
import com.example.concurrent.countDownLatch.Worker;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class CountdownLatchExampleIntegrationTest {
    @Test
    public void whenParallelProcessing_thenMainThreadWillBlockUntilCompletion() throws InterruptedException {
//        به طور طبیعی "چفت منتشر شد" همیشه آخرین خروجی خواهد بود - زیرا به انتشار CountDownLatch بستگی دارد.
//
//توجه داشته باشید که اگر await() را فراخوانی نمی‌کردیم، نمی‌توانیم ترتیب اجرای رشته‌ها را تضمین کنیم، بنابراین آزمایش به‌طور تصادفی با شکست مواجه می‌شد.

        // Given
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new Worker(outputScraper, countDownLatch)))
          .limit(5)
          .collect(toList());

        // When
        workers.forEach(Thread::start);
        countDownLatch.await(); // Block until workers finish
        outputScraper.add("Latch released");

        // Then
        assertThat(outputScraper).containsExactly("Counted down", "Counted down", "Counted down", "Counted down", "Counted down", "Latch released");
    }

    @Test
    public void whenFailingToParallelProcess_thenMainThreadShouldTimeout() throws InterruptedException {
        // Given
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new BrokenWorker(outputScraper, countDownLatch)))
          .limit(5)
          .collect(toList());

        // When
        workers.forEach(Thread::start);
        final boolean result = countDownLatch.await(3L, TimeUnit.SECONDS);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void whenDoingLotsOfThreadsInParallel_thenStartThemAtTheSameTime() throws InterruptedException {
//        اگر مثال قبلی را در نظر گرفتیم، اما این بار به جای پنج، هزاران رشته را شروع کردیم، احتمالاً بسیاری از رشته‌های قبلی قبل از فراخوانی start() در موردهای بعدی، پردازش را تمام کرده باشند. این می‌تواند تلاش و بازتولید یک مشکل همزمانی را دشوار کند، زیرا نمی‌توانیم همه رشته‌های خود را به صورت موازی اجرا کنیم.
//
//برای دور زدن این موضوع، اجازه دهید CountdownLatch متفاوت از مثال قبلی کار کند. به جای مسدود کردن یک رشته اصلی تا زمانی که برخی از رشته‌های فرزند به پایان برسد، می‌توانیم هر رشته فرزند را تا زمانی که همه رشته‌های دیگر شروع شوند مسدود کنیم.
//
//بیایید متد run() خود را تغییر دهیم تا قبل از پردازش بلاک شود:


//اکنون، بیایید آزمایش خود را تغییر دهیم تا زمانی که همه Workers شروع به کار کنند، مسدود شود، Workers رفع انسداد شود، و سپس بلاک شود تا زمانی که Workers تمام شود:
//        این الگو واقعاً برای تلاش برای بازتولید باگ‌های همزمانی مفید است، زیرا می‌توان از آن برای وادار کردن هزاران رشته به تلاش و اجرای منطقی موازی استفاده کرد.

        // Given
        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch readyThreadCounter = new CountDownLatch(5);
        CountDownLatch callingThreadBlocker = new CountDownLatch(1);
        CountDownLatch completedThreadCounter = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new WaitingWorker(outputScraper, readyThreadCounter, callingThreadBlocker, completedThreadCounter))).limit(5).collect(toList());

        // When
        workers.forEach(Thread::start);
        readyThreadCounter.await(); // Block until workers start
        outputScraper.add("Workers ready");
        callingThreadBlocker.countDown(); // Start workers
        completedThreadCounter.await(); // Block until workers finish
        outputScraper.add("Workers complete");

        // Then
        assertThat(outputScraper).containsExactly("Workers ready", "Counted down", "Counted down", "Counted down", "Counted down", "Counted down", "Workers complete");
    }

}