package com.example.threadpool;

import com.example.threadPool.CountingTask;
import com.example.threadPool.TreeNode;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class CoreThreadPoolIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(CoreThreadPoolIntegrationTest.class);
//کلاس کمکی Executors شامل چندین روش برای ایجاد نمونه های thread pool از پیش پیکربندی شده است. این کلاس ها محل خوبی برای شروع هستند. اگر نیازی به اعمال تنظیمات دقیق سفارشی نداشته باشیم، می توانیم از آنها استفاده کنیم.
//
//ما از رابط های Executor و ExecutorService برای کار با پیاده سازی های مختلف Thread Pool در جاوا استفاده می کنیم. معمولا، ما باید کد خود را از اجرای واقعی Thread Pool جدا نگه داریم و از این رابط ها در سراسر برنامه خود استفاده کنیم.
//
//3.1.1. مجری
//رابط Executor دارای یک روش اجرای واحد برای ارسال نمونه های Runnable برای اجرا می باشد.
//
//بیایید به یک مثال سریع از نحوه استفاده از Executors API برای به دست آوردن یک نمونه Executor با پشتیبانی از یک Thread Pool و یک صف نامحدود برای اجرای کارها به صورت متوالی نگاه کنیم.
//
//در اینجا، ما یک کار را اجرا می کنیم که به سادگی "Hello World" را روی صفحه نمایش می دهد. ما کار را به صورت لامبدا (ویژگی جاوا 8) ارسال می کنیم که به نظر می رسد Runnable است:
    @Test(timeout = 1000)
    public void whenCallingExecuteWithRunnable_thenRunnableIsExecuted() throws InterruptedException {
//رابط ExecutorService شامل تعداد زیادی روش برای کنترل پیشرفت کارها و مدیریت خاتمه سرویس است. با استفاده از این رابط می توانیم وظایف را برای اجرا ارسال کنیم و همچنین اجرای آنها را با استفاده از نمونه بازگشتی Future کنترل کنیم.
//
//اکنون یک ExecutorService ایجاد می کنیم، یک وظیفه ارسال می کنیم و سپس از متد بازگشتی Future’s get استفاده می کنیم تا منتظر بمانیم تا کار ارسالی تمام شود و مقدار برگردانده شود:
//        رابط ExecutorService شامل تعداد زیادی روش برای کنترل پیشرفت کارها و مدیریت خاتمه سرویس است. با استفاده از این رابط می توانیم وظایف را برای اجرا ارسال کنیم و همچنین اجرای آنها را با استفاده از نمونه بازگشتی Future کنترل کنیم.
//
//اکنون یک ExecutorService ایجاد می کنیم، یک وظیفه ارسال می کنیم و سپس از متد بازگشتی Future’s get استفاده می کنیم تا منتظر بمانیم تا کار ارسالی تمام شود و مقدار برگردانده شود:
        CountDownLatch lock = new CountDownLatch(1);

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            LOG.debug("Hello World");
            lock.countDown();
        });

        lock.await(1000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void whenUsingExecutorServiceAndFuture_thenCanWaitOnFutureResult() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> future = executorService.submit(() -> "Hello World");
        String result = future.get();

        assertEquals("Hello World", result);

    }

    @Test
    public void whenUsingFixedThreadPool_thenCoreAndMaximumThreadSizeAreTheSame() {
        //        ThreadPoolExecutor یک پیاده‌سازی thread pool با تعداد زیادی پارامتر و قلاب برای تنظیم دقیق است.
//
//پارامترهای اصلی پیکربندی که در اینجا به آنها اشاره خواهیم کرد عبارتند از corePoolSize، maximumPoolSize و keepAliveTime.
//
//این استخر از تعداد ثابتی از نخ های هسته تشکیل شده است که همیشه در داخل آن نگهداری می شوند. همچنین شامل تعدادی نخ بیش از حد است که ممکن است تخم ریزی شوند و زمانی که دیگر مورد نیاز نیستند، خاتمه دهند.
//
//پارامتر corePoolSize تعداد رشته‌های هسته‌ای است که نمونه‌سازی می‌شوند و در استخر نگهداری می‌شوند. هنگامی که یک کار جدید وارد می شود، اگر تمام رشته های اصلی مشغول باشند و صف داخلی پر باشد، استخر مجاز است تا حداکثر PoolSize رشد کند.
//
//پارامتر keepAliveTime بازه زمانی است که برای آن رشته‌های بیش از حد (که بیش از corePoolSize نمونه‌سازی شده‌اند) اجازه دارند در حالت بیکار وجود داشته باشند. به طور پیش فرض، ThreadPoolExecutor فقط رشته های غیر هسته ای را برای حذف در نظر می گیرد. به منظور اعمال سیاست حذف یکسان برای رشته های اصلی، می توانیم از روش allowCoreThreadTimeOut(true) استفاده کنیم.
//
//این پارامترها طیف گسترده ای از موارد استفاده را پوشش می دهند، اما معمولی ترین پیکربندی ها در روش های استاتیک Executors از پیش تعریف شده اند.

//بیایید به یک مثال نگاه کنیم. متد newFixedThreadPool یک ThreadPoolExecutor با مقادیر پارامتر corePoolSize و maximumPoolSize برابر و یک keepAliveTime صفر ایجاد می کند. این به این معنی است که تعداد رشته‌ها در این مجموعه موضوعی همیشه یکسان است:

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        assertEquals(2, executor.getPoolSize());
        assertEquals(1, executor.getQueue().size());

    }

    @Test
    public void whenUsingCachedThreadPool_thenPoolSizeGrowsUnbounded() {
//        می‌توانیم یک ThreadPoolExecutor از پیش پیکربندی‌شده دیگر با متد Executors.newCachedThreadPool ایجاد کنیم. این روش به هیچ وجه تعدادی نخ دریافت نمی کند. ما corePoolSize را روی 0 قرار دادیم و حداکثر PoolSize را بر روی Integer.MAX_VALUE قرار دادیم. در نهایت، keepAliveTime 60 ثانیه است:

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        assertEquals(3, executor.getPoolSize());
        assertEquals(0, executor.getQueue().size());

    }

    @Test(timeout = 1000)
    public void whenUsingSingleThreadPool_thenTasksExecuteSequentially() throws InterruptedException {
//API Executors.newSingleThreadExecutor شکل معمولی دیگری از ThreadPoolExecutor را ایجاد می کند که حاوی یک رشته است. مجری تک رشته برای ایجاد یک حلقه رویداد ایده آل است. پارامترهای corePoolSize و maximumPoolSize برابر با 1 و keepAliveTime 0 است.
//
//وظایف در مثال بالا به صورت متوالی اجرا می شوند، بنابراین پس از اتمام کار، مقدار پرچم 2 خواهد بود:
//        علاوه بر این، این ThreadPoolExecutor با یک لفاف غیر قابل تغییر تزئین شده است، بنابراین نمی توان آن را پس از ایجاد دوباره پیکربندی کرد. توجه داشته باشید که این دلیلی است که ما نمی توانیم آن را به ThreadPoolExecutor ارسال کنیم.
        CountDownLatch lock = new CountDownLatch(2);
        AtomicInteger counter = new AtomicInteger();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            counter.set(1);
            lock.countDown();
        });
        executor.submit(() -> {
            counter.compareAndSet(1, 2);
            lock.countDown();
        });

        lock.await(1000, TimeUnit.MILLISECONDS);
        assertEquals(2, counter.get());

    }

    @Test(timeout = 1000)
    public void whenSchedulingTask_thenTaskExecutesWithinGivenPeriod() throws InterruptedException {
//        ScheduledThreadPoolExecutor کلاس ThreadPoolExecutor را گسترش می دهد و همچنین رابط ScheduledExecutorService را با چندین روش اضافی پیاده سازی می کند:
//
//روش زمانبندی به ما این امکان را می دهد که پس از یک تاخیر مشخص، یک کار را یک بار اجرا کنیم.
//متد scheduleAtFixedRate به ما این امکان را می دهد که یک کار را پس از یک تاخیر اولیه مشخص اجرا کنیم و سپس آن را به طور مکرر با یک دوره مشخص اجرا کنیم. آرگومان دوره زمانی است که بین زمان‌های شروع کارها اندازه‌گیری می‌شود، بنابراین نرخ اجرا ثابت است.
//متد scheduleWithFixedDelay شبیه scheduleAtFixedRate است که به طور مکرر وظیفه داده شده را اجرا می کند، اما تاخیر مشخص شده بین پایان کار قبلی و شروع کار بعدی اندازه گیری می شود. نرخ اجرا ممکن است بسته به زمان لازم برای اجرای هر کار مشخص متفاوت باشد.
//ما معمولاً از متد Executors.newScheduledThreadPool() برای ایجاد یک ScheduledThreadPoolExecutor با corePoolSize معین، حداکثرPoolSize نامحدود و صفر keepAliveTime استفاده می کنیم.
//
//در اینجا نحوه برنامه ریزی یک کار برای اجرا در 500 میلی ثانیه آمده است:

        CountDownLatch lock = new CountDownLatch(1);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        executor.schedule(() -> {
            LOG.debug("Hello World");
            lock.countDown();
        }, 500, TimeUnit.MILLISECONDS);

        lock.await(1000, TimeUnit.MILLISECONDS);

    }

    @Test(timeout = 1000)
    public void whenSchedulingTaskWithFixedPeriod_thenTaskExecutesMultipleTimes() throws InterruptedException {
//کد زیر نشان می دهد که چگونه می توان یک کار را پس از 500 میلی ثانیه تاخیر اجرا کرد و سپس آن را هر 100 میلی ثانیه تکرار کرد. پس از زمان‌بندی کار، با استفاده از قفل CountDownLatch، منتظر می‌مانیم تا سه بار فعال شود. سپس با استفاده از متد Future.cancel آن را لغو می کنیم:

        CountDownLatch lock = new CountDownLatch(3);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            LOG.debug("Hello World");
            lock.countDown();
        }, 500, 100, TimeUnit.MILLISECONDS);

        lock.await();
        future.cancel(true);

    }

    @Test
    public void whenUsingForkJoinPool_thenSumOfTreeElementsIsCalculatedCorrectly() {
//        ForkJoinPool بخش مرکزی چارچوب fork/join است که در جاوا 7 معرفی شده است. این یک مشکل رایج ایجاد چندین کار در الگوریتم های بازگشتی را حل می کند. با استفاده از یک ThreadPoolExecutor ساده، رشته ها به سرعت تمام می شوند، زیرا هر کار یا وظیفه فرعی برای اجرا نیاز به رشته خاص خود دارد.
//
//در چارچوب فورک/پیوستن، هر کاری می‌تواند تعدادی زیرکار را ایجاد کند و منتظر تکمیل آن‌ها با استفاده از روش Join باشد. مزیت چارچوب فورک/پیوستن این است که یک رشته جدید برای هر کار یا زیرکار ایجاد نمی کند، در عوض الگوریتم سرقت کار را پیاده سازی می کند.

        TreeNode tree = new TreeNode(5, new TreeNode(3), new TreeNode(2, new TreeNode(2), new TreeNode(8)));

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int sum = forkJoinPool.invoke(new CountingTask(tree));

        assertEquals(20, sum);
    }

}
