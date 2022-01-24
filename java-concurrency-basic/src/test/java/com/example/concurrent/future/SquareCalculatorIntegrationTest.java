package com.example.concurrent.future;

import com.example.future.SquareCalculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SquareCalculatorIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(SquareCalculatorIntegrationTest.class);

    @Rule
    public TestName name = new TestName();

    private long start;

    private SquareCalculator squareCalculator;

    @Test
    public void givenExecutorIsSingleThreaded_whenTwoExecutionsAreTriggered_thenRunInSequence() throws InterruptedException, ExecutionException {
        squareCalculator = new SquareCalculator(Executors.newSingleThreadExecutor());
//اکنون باید calculate() را فراخوانی کنیم و از Future برگشتی برای بدست آوردن عدد صحیح حاصل استفاده کنیم. دو روش از Future API به ما در انجام این کار کمک می کند.
//
//Future.isDone() به ما می گوید که آیا مجری پردازش کار را به پایان رسانده است. اگر کار کامل شود، به درستی باز می گردد. در غیر این صورت، false برمی گردد.
//
//متدی که نتیجه واقعی محاسبه را برمی گرداند Future.get () است. می بینیم که این روش اجرا را تا زمانی که کار کامل شود مسدود می کند. با این حال، این مشکل در مثال ما نخواهد بود، زیرا ما با فراخوانی isDone() بررسی خواهیم کرد که آیا کار کامل شده است یا خیر.
//
//با استفاده از این دو روش، می توانیم کدهای دیگری را در حالی که منتظر اتمام کار اصلی هستیم اجرا کنیم:
        Future<Integer> result1 = squareCalculator.calculate(4);
        Future<Integer> result2 = squareCalculator.calculate(1000);

        while (!result1.isDone() || !result2.isDone()) {
            LOG.debug(String.format("Task 1 is %s and Task 2 is %s.", result1.isDone() ? "done" : "not done", result2.isDone() ? "done" : "not done"));

            Thread.sleep(300);
        }

        assertEquals(16, result1.get().intValue());
        assertEquals(1000000, result2.get().intValue());
    }

    @Test(expected = TimeoutException.class)
    public void whenGetWithTimeoutLowerThanExecutionTime_thenThrowException() throws InterruptedException, ExecutionException, TimeoutException {
        squareCalculator = new SquareCalculator(Executors.newSingleThreadExecutor());

        Future<Integer> result = squareCalculator.calculate(4);
//        تفاوت بین get(long، TimeUnit) و get() در این است که اگر وظیفه قبل از بازه زمانی مشخص شده بازنگردد، یک TimeoutException ایجاد می کند.
        result.get(500, TimeUnit.MILLISECONDS);
    }

    @Test
    public void givenExecutorIsMultiThreaded_whenTwoExecutionsAreTriggered_thenRunInParallel() throws InterruptedException, ExecutionException {
//        ExecutorService فعلی ما تک رشته ای است، زیرا با Executors.newSingleThreadExecutor به دست آمده است. برای برجسته کردن این رشته، اجازه دهید دو محاسبه را به طور همزمان آغاز کنیم:
        squareCalculator = new SquareCalculator(Executors.newFixedThreadPool(2));
//واضح است که روند موازی نیست. می‌توانیم ببینیم که کار دوم تنها زمانی شروع می‌شود که کار اول کامل شود، که باعث می‌شود کل فرآیند حدود 2 ثانیه طول بکشد.
//
//برای اینکه برنامه ما واقعاً چند رشته ای باشد، باید از طعم متفاوت ExecutorService استفاده کنیم. بیایید ببینیم اگر از یک Thread Pool ارائه شده توسط متد کارخانه ای () Executors.newFixedThreadPool استفاده کنیم، رفتار مثال ما چگونه تغییر می کند:
//        این در حال حاضر بسیار بهتر به نظر می رسد. می بینیم که 2 کار به طور همزمان شروع و به پایان می رسند و کل فرآیند حدود 1 ثانیه طول می کشد تا کامل شود.
//
//روش‌های کارخانه‌ای دیگری نیز وجود دارد که می‌توان از آنها برای ایجاد thread pool استفاده کرد، مانند Executors.newCachedThreadPool()، که از Thread‌های قبلاً استفاده شده در زمانی که در دسترس هستند، مجدداً استفاده می‌کند، و Executors.newScheduledThreadPool()، که دستورات را برای اجرای پس از تأخیر مشخص زمان‌بندی می‌کند.
        Future<Integer> result1 = squareCalculator.calculate(4);
        Future<Integer> result2 = squareCalculator.calculate(1000);

        while (!result1.isDone() || !result2.isDone()) {
            LOG.debug(String.format("Task 1 is %s and Task 2 is %s.", result1.isDone() ? "done" : "not done", result2.isDone() ? "done" : "not done"));

            Thread.sleep(300);
        }

        assertEquals(16, result1.get().intValue());
        assertEquals(1000000, result2.get().intValue());
    }

    @Test(expected = CancellationException.class)
    public void whenCancelFutureAndCallGet_thenThrowException() throws InterruptedException, ExecutionException, TimeoutException {
        squareCalculator = new SquareCalculator(Executors.newSingleThreadExecutor());

        Future<Integer> result = squareCalculator.calculate(4);
//        فرض کنید یک کار را راه اندازی کرده ایم، اما بنا به دلایلی، دیگر به نتیجه اهمیت نمی دهیم. می‌توانیم از Future.cancel(boolean) استفاده کنیم تا به مجری بگوییم عملیات را متوقف کند و رشته زیرین آن را قطع کند:
//        نمونه ما از Future، از کد بالا، هرگز عملیات خود را کامل نخواهد کرد. در واقع، اگر سعی کنیم get() را از آن نمونه فراخوانی کنیم، پس از فراخوانی cancel()، نتیجه یک CancellationException خواهد بود. Future.isCancelled() به ما می گوید که آیا یک Future قبلاً لغو شده است یا خیر. این می تواند برای جلوگیری از دریافت CancellationException بسیار مفید باشد.
//
//همچنین ممکن است فراخوانی برای لغو() با شکست مواجه شود. در این صورت مقدار برگشتی نادرست خواهد بود. توجه به این نکته مهم است که cancel() یک مقدار بولی را به عنوان آرگومان می گیرد. این کنترل می کند که آیا رشته ای که وظیفه را اجرا می کند باید قطع شود یا خیر.

        boolean canceled = result.cancel(true);

        assertTrue("Future was canceled", canceled);
        assertTrue("Future was canceled", result.isCancelled());

        result.get();
    }

    @Before
    public void start() {
        start = System.currentTimeMillis();
    }

    @After
    public void end() {
        LOG.debug(String.format("Test %s took %s ms \n", name.getMethodName(), System.currentTimeMillis() - start));
    }
}
