package com.example.rejection;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SaturationPolicyUnitTest {

    private ThreadPoolExecutor executor;

    @After
    public void shutdownExecutor() {
        if (executor != null && !executor.isTerminated()) {
            executor.shutdownNow();
        }
    }

    @Ignore
    @Test
    public void givenAbortPolicy_WhenSaturated_ThenShouldThrowRejectedExecutionException() {
//        خط مشی پیش فرض خط مشی abort است. خط مشی Abort باعث می شود که مجری یک RejectedExecutionException را پرتاب کند:
//        از آنجایی که اجرای وظیفه اول زمان زیادی می برد، مجری وظیفه دوم را رد می کند.
        executor = new ThreadPoolExecutor(1, 1, 0, MILLISECONDS, new SynchronousQueue<>(), new AbortPolicy());
        executor.execute(() -> waitFor(250));

        assertThatThrownBy(() -> executor.execute(() -> System.out.println("Will be rejected"))).isInstanceOf(RejectedExecutionException.class);
    }

    @Ignore
    @Test
    public void givenCallerRunsPolicy_WhenSaturated_ThenTheCallerThreadRunsTheTask() {
//        به جای اجرای یک کار به صورت ناهمزمان در رشته دیگر، این خط مشی باعث می شود تا رشته تماس گیرنده کار را اجرا کند:
//
//پس از ارسال اولین وظیفه، مجری نمی تواند کارهای جدید دیگری را بپذیرد. بنابراین، رشته تماس گیرنده تا زمانی که وظیفه دوم برگردد مسدود می شود.
//
//سیاست اجرای تماس گیرنده اجرای یک شکل ساده از throttling را آسان می کند. یعنی یک مصرف کننده کند می تواند تولید کننده سریع را برای کنترل جریان ارسال کار کند کند.
        executor = new ThreadPoolExecutor(1, 1, 0, MILLISECONDS, new SynchronousQueue<>(), new CallerRunsPolicy());
        executor.execute(() -> waitFor(250));

        long startTime = System.currentTimeMillis();
        executor.execute(() -> waitFor(500));
        long blockedDuration = System.currentTimeMillis() - startTime;

        assertThat(blockedDuration).isGreaterThanOrEqualTo(500);
    }

    @Test
    public void givenDiscardPolicy_WhenSaturated_ThenExecutorDiscardsTheNewTask() throws InterruptedException {
//        خط‌مشی صرف‌نظر کردن، وقتی وظیفه جدید را ارسال نمی‌کند، بی‌صدا آن را کنار می‌گذارد:
//
//در اینجا، وظیفه دوم یک پیام ساده را در یک صف منتشر می کند. از آنجایی که هرگز فرصتی برای اجرا پیدا نمی کند، صف خالی می ماند، حتی اگر برای مدتی آن را مسدود کنیم.
        executor = new ThreadPoolExecutor(1, 1, 0, MILLISECONDS, new SynchronousQueue<>(), new DiscardPolicy());
        executor.execute(() -> waitFor(100));

        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        executor.execute(() -> queue.offer("Result"));

        assertThat(queue.poll(200, MILLISECONDS)).isNull();
    }

    @Test
    public void givenDiscardOldestPolicy_WhenSaturated_ThenExecutorDiscardsTheOldestTask() {
//        خط‌مشی قدیمی‌ترین حذف، ابتدا یک کار را از سر صف حذف می‌کند، سپس کار جدید را دوباره ارسال می‌کند:
//
//این بار، ما از یک صف محدود استفاده می کنیم که می تواند فقط دو وظیفه را در خود جای دهد. وقتی این چهار کار را ارسال می کنیم، این اتفاق می افتد:
//
//اولین کارها تک رشته را به مدت 100 میلی ثانیه نگه می دارند
//مجری وظایف دوم و سوم را با موفقیت در صف قرار می دهد
//هنگامی که وظیفه چهارم می رسد، خط مشی قدیمی ترین را حذف می کند تا جایی برای این کار جدید ایجاد کند.
//قدیمی‌ترین خط‌مشی و صف‌های اولویت با هم به خوبی بازی نمی‌کنند. از آنجایی که سر صف اولویت بالاترین اولویت را دارد، ممکن است به سادگی مهمترین وظیفه را از دست بدهیم.
        executor = new ThreadPoolExecutor(1, 1, 0, MILLISECONDS, new ArrayBlockingQueue<>(2), new DiscardOldestPolicy());
        executor.execute(() -> waitFor(100));

        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        executor.execute(() -> queue.offer("First"));
        executor.execute(() -> queue.offer("Second"));
        executor.execute(() -> queue.offer("Third"));

        waitFor(150);
        List<String> results = new ArrayList<>();
        queue.drainTo(results);
        assertThat(results).containsExactlyInAnyOrder("Second", "Third");
    }

    @Test
    public void givenGrowPolicy_WhenSaturated_ThenExecutorIncreaseTheMaxPoolSize() {

        executor = new ThreadPoolExecutor(1, 1, 0, MILLISECONDS, new ArrayBlockingQueue<>(2), new GrowPolicy());
        executor.execute(() -> waitFor(100));

        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        executor.execute(() -> queue.offer("First"));
        executor.execute(() -> queue.offer("Second"));
        executor.execute(() -> queue.offer("Third"));

        waitFor(150);
        List<String> results = new ArrayList<>();
        queue.drainTo(results);
        assertThat(results).containsExactlyInAnyOrder("First", "Second", "Third");
    }

    @Test
    public void givenExecutorIsTerminated_WhenSubmittedNewTask_ThenTheSaturationPolicyApplies() {
//        علاوه بر مجری‌های بارگذاری شده، سیاست‌های اشباع برای همه مجری‌هایی که تعطیل شده‌اند نیز اعمال می‌شود:
//
//همین امر برای همه مجریانی که در وسط خاموشی هستند صادق است:
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, MILLISECONDS, new LinkedBlockingQueue<>());
        executor.shutdownNow();

        assertThatThrownBy(() -> executor.execute(() -> {
        })).isInstanceOf(RejectedExecutionException.class);
    }

    @Test
    public void givenExecutorIsTerminating_WhenSubmittedNewTask_ThenTheSaturationPolicyApplies() {
        //        همچنین فقط با اجرای رابط RejectedExecutionHandler می توان یک خط مشی اشباع سفارشی ارائه کرد:
//
//در این مثال، وقتی اجرا کننده اشباع می شود، حداکثر اندازه استخر را یک افزایش می دهیم و سپس همان کار را دوباره ارسال می کنیم:
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, MILLISECONDS, new LinkedBlockingQueue<>());
        executor.execute(() -> waitFor(100));
        executor.shutdown();

        assertThatThrownBy(() -> executor.execute(() -> {
        })).isInstanceOf(RejectedExecutionException.class);
    }

    private static class GrowPolicy implements RejectedExecutionHandler {

        private final Lock lock = new ReentrantLock();

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            lock.lock();
            try {
                executor.setMaximumPoolSize(executor.getMaximumPoolSize() + 1);
            } finally {
                lock.unlock();
            }

            executor.submit(r);
        }
    }

    private void waitFor(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }
}
