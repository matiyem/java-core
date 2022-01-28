package com.example.concurrent.stopExecution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;


/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 1:04 PM
**/
public class StopExecution {
//    هیچ تضمینی وجود ندارد که اجرای پس از یک زمان معین متوقف شود. دلیل اصلی این است که همه روش های مسدود کردن قابل وقفه نیستند. در واقع، تنها چند روش کاملاً تعریف شده وجود دارد که قابل وقفه هستند. بنابراین، اگر یک رشته قطع شود و یک پرچم تنظیم شود، تا زمانی که به یکی از این روش‌های قطع‌پذیر برسد، هیچ اتفاق دیگری نمی‌افتد.
//
//برای مثال، روش‌های خواندن و نوشتن تنها در صورتی قابل وقفه هستند که در جریان‌های ایجاد شده با کانال Interruptible فراخوانی شوند. BufferedReader یک کانال قطعی نیست. بنابراین، اگر thread از آن برای خواندن یک فایل استفاده کند، فراخوانی interrupt() در این رشته مسدود شده در متد read هیچ تاثیری ندارد.
//
//با این حال، ما می توانیم به صراحت پرچم وقفه را پس از هر بار خواندن در یک حلقه بررسی کنیم. این تضمین معقولی برای متوقف کردن موضوع با کمی تاخیر می دهد. اما، این تضمینی برای متوقف کردن موضوع پس از یک زمان سخت نیست، زیرا ما نمی دانیم که یک عملیات خواندن چقدر زمان می برد.
//
//از طرف دیگر، متد انتظار از کلاس Object قابل وقفه است. بنابراین، رشته مسدود شده در روش انتظار، بلافاصله پس از تنظیم پرچم وقفه، یک InterruptedException ایجاد می کند.
//
//ما می‌توانیم روش‌های مسدود کردن را با جستجوی یک throws InterruptedException در امضای متد آنها شناسایی کنیم.
//
//یکی از توصیه های مهم این است که از استفاده از متد () Thread.stop منسوخ شده خودداری کنید. متوقف کردن نخ باعث می شود که قفل تمام مانیتورهایی که قفل کرده است باز شود. این به دلیل استثنای ThreadDeath است که در پشته منتشر می شود.
//
//اگر هر یک از اشیایی که قبلاً توسط این مانیتورها محافظت شده بود، در حالت ناسازگار بود، اشیاء ناسازگار برای رشته‌های دیگر قابل مشاهده می‌شوند. این می تواند منجر به رفتار خودسرانه ای شود که تشخیص و استدلال در مورد آن بسیار سخت است.
    private static final Logger LOG = LoggerFactory.getLogger(StopExecution.class);

    public static void main(String[] args) {
        StopExecution stopExecution = new StopExecution();
        stopExecution.testScheduledExecutor();
        LOG.info("done");
    }

    public void testUsingLoop() {
        long start = System.currentTimeMillis();
        long end = start + 5000;
        List<String> items = new ArrayList<>();
        int counter = 0;
        while (System.currentTimeMillis() < end && counter < items.size()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        }
    }

    public static void testThreads() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                LOG.info("inside run");

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOG.info("exit run");
            }
        });
        thread.start();
        while (thread.getState() != Thread.State.TERMINATED) {
            LOG.info(thread.getState().name());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void testExecutor2() {
//        /        همچنین می‌توانیم به‌جای استفاده از تایمر از متد get یک Future استفاده کنیم:
////        در اینجا، ما از ExecutorService برای ارسال thread کارگری استفاده کردیم که نمونه‌ای از Future را برمی‌گرداند، که متد get آن تا زمان مشخص شده موضوع اصلی را مسدود می‌کند. این یک TimeoutException بعد از مهلت زمانی مشخص شده ایجاد می کند. در بلوک catch، با فراخوانی متد cancel در شی Future، موضوع کارگر را قطع می کنیم.
////
////مزیت اصلی این روش نسبت به روش قبلی این است که از یک Pool برای مدیریت thread استفاده می کند، در حالی که Timer تنها از یک رشته (بدون استخر) استفاده می کند.
        final ExecutorService service = Executors.newSingleThreadExecutor();
        Future f = null;

        try {
            f = service.submit(new LongRunningTask());
            LOG.info("testExecutor2");
            f.get(1, TimeUnit.SECONDS);
        } catch (final TimeoutException e) {
            f.cancel(true);
            LOG.error("Calculation took to long");
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            service.shutdownNow();
        }


    }

    public void testScheduledExecutor() {
//        همچنین می توانیم از ScheduledExecutorService برای قطع کار استفاده کنیم. این کلاس توسعه یک ExecutorService است و با اضافه کردن چندین روش که با زمان‌بندی اجرا سروکار دارند، عملکرد مشابهی را ارائه می‌کند. این می تواند وظیفه داده شده را پس از تاخیر معینی از واحدهای زمانی تنظیم شده اجرا کند:
//        در اینجا، با روش newScheduledThreadPool یک thread pool زمانبندی شده در اندازه دو ایجاد کردیم. متد ScheduledExecutorService#schedule یک Runnable، یک مقدار تاخیر و واحد تاخیر را می گیرد.
//
//برنامه فوق پس از یک ثانیه از زمان ارسال، این کار را زمانبندی می کند. این کار وظیفه طولانی مدت اصلی را لغو می کند.
//
//توجه داشته باشید که برخلاف روش قبلی، ما با فراخوانی متد Future#get، رشته اصلی را مسدود نمی‌کنیم. بنابراین، در بین تمام رویکردهای ذکر شده، ترجیح داده شده است.
        LOG.info("testScheduledExecutor");
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        Future future = executor.submit(new LongRunningTask());
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                future.cancel(true);

            }
        }, 100, TimeUnit.MICROSECONDS);
        executor.shutdown();
    }

    public void textThreadAndInterrupt() {
        Thread t;
        try {
            t = new Thread(new LongRunningTask());

            LOG.info("testExecutors3");
            long end = System.currentTimeMillis() + 2000;
            t.start();
            while (t.isAlive() && System.currentTimeMillis() < end) {
                Thread.sleep(50);
            }
            t.interrupt();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void testTimer() {
        LOG.info("Timer test");
        Thread t = new Thread(new LongRunningTask());
        Timer timeOutTimer = new Timer();
        timeOutTimer.schedule(new TimeOutTask(t, timeOutTimer), 1000);
        ;
        t.start();
    }

    class MyRunnableTask implements Runnable {

        @Override
        public void run() {
            try {
                LOG.info("MyRunnable...");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                LOG.info("MyRunnable interrupted...");
            }
        }
    }

    class TimeOutTask extends TimerTask {
//        از طرف دیگر، می‌توانیم یک TimerTask ایجاد کنیم تا رشته کارگر پس از اتمام زمان قطع شود:
        private Thread t;
        private Timer timer;
//        در اینجا، ما یک TimerTask تعریف کرده‌ایم که در زمان ایجاد یک نخ کارگر می‌گیرد. با فراخوانی روش اجرا، رشته کارگر را قطع می‌کند. تایمر پس از تأخیر مشخص شده TimerTask را فعال می کند:

        TimeOutTask(Thread t, Timer timer) {
            this.t = t;
            this.timer = timer;
        }

        @Override
        public void run() {
//            علاوه بر وضعیت thread، می‌توانیم متد isAlive() را بررسی کنیم تا مشخص کنیم که آیا نخ فعال است یا نه. به عنوان مثال، اگر متد isAlive() را در این رشته فراخوانی کنیم:
//            false برمیگرده به زبان ساده، یک رشته زنده است اگر و تنها در صورتی که شروع شده باشد و هنوز نمرده باشد.
            if (t != null && t.isAlive()) {
                t.interrupt();
                timer.cancel();
            }

        }
    }

    class LongRunningTask implements Runnable {
//        در اینجا، ما از یک موضوع جداگانه برای انجام عملیات طولانی مدت استفاده می کنیم. رشته اصلی سیگنال وقفه ای را به نخ کارگر در زمان وقفه ارسال می کند.
//
//اگر thread کارگر هنوز زنده باشد، سیگنال را می گیرد و اجرای آن را متوقف می کند. اگر کارگر قبل از مهلت پایان کار را تمام کند، هیچ تاثیری روی نخ کارگر نخواهد داشت.
//
//بیایید نگاهی به تاپیک کارگر بیندازیم:

        @Override
        public void run() {

        }

        private void longRunningSort() {
            LOG.info("Long running task started");
            int len = 100000;
            List<Integer> numbers = new ArrayList<>();
            try {
                for (int i = len; i > 0; i--) {
                    numbers.add(i);
                }
                int i = 0;
                for (i = 0; i < len; i++) {
                    int minIndex = i;
                    for (int j = i + 1; j < len; j++) {
                        if (numbers.get(minIndex) > numbers.get(j)) {
                            minIndex = j;
                        }
                    }
                    if (minIndex != i) {
                        int temp = numbers.get(i);
                        numbers.set(i, numbers.get(minIndex));
                        numbers.set(minIndex, temp);
                    }
                    throwExceptionOnThreadInterrupt();
                }
                LOG.info("Index position: " + i);
                LOG.info("Long running task finished");
            } catch (InterruptedException e) {
                LOG.info("Long running operation interrupted");
            }
        }

        private void throwExceptionOnThreadInterrupt() throws InterruptedException {
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }
        }
    }

}
