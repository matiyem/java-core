package com.example.concurrent.adder;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

import static com.jayway.awaitility.Awaitility.await;
import static junit.framework.TestCase.assertEquals;

public class LongAdderUnitTest {
//    در این مقاله، دو ساختار از بسته java.util.concurrent را بررسی خواهیم کرد: LongAdder و LongAccumulator.
//
//هر دو به گونه‌ای ساخته شده‌اند که در محیط چند رشته‌ای بسیار کارآمد باشند و هر دو از تاکتیک‌های بسیار هوشمندانه استفاده می‌کنند تا بدون قفل باشند و همچنان در برابر نخ‌ها ایمن بمانند.
    @Test
    public void givenMultipleThread_whenTheyWriteToSharedLongAdder_thenShouldCalculateSumForThem() throws InterruptedException {
//بیایید منطقی را در نظر بگیریم که برخی از مقادیر را اغلب افزایش می دهد، جایی که استفاده از AtomicLong می تواند یک گلوگاه باشد. این از یک عملیات مقایسه و تعویض استفاده می‌کند، که - تحت بحث‌های سنگین - می‌تواند منجر به هدر رفتن چرخه‌های CPU شود.
//
//از سوی دیگر، LongAdder از یک ترفند بسیار هوشمندانه برای کاهش اختلاف بین رشته‌ها استفاده می‌کند، زمانی که آنها آن را افزایش می‌دهند.
//
//وقتی می خواهیم نمونه ای از LongAdder را افزایش دهیم، باید متد ()increment را فراخوانی کنیم. این پیاده سازی مجموعه ای از شمارنده ها را نگه می دارد که می توانند در صورت تقاضا رشد کنند.
//
//و بنابراین، هنگامی که رشته های بیشتری در حال فراخوانی increment() هستند، آرایه طولانی تر خواهد بود. هر رکورد در آرایه را می توان به طور جداگانه به روز کرد - کاهش اختلاف. با توجه به این واقعیت، LongAdder یک راه بسیار کارآمد برای افزایش شمارنده از چندین رشته است.
//
//بیایید یک نمونه از کلاس LongAdder ایجاد کنیم و آن را از چندین رشته به روز کنیم:

        //given
        LongAdder counter = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        int numberOfThreads = 4;
        int numberOfIncrements = 100;

        //when
        Runnable incrementAction = () -> IntStream
          .range(0, numberOfIncrements)
          .forEach((i) -> counter.increment());

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(incrementAction);
        }
//تا زمانی که متد sum() را فراخوانی نکنیم، نتیجه شمارنده در LongAdder در دسترس نیست. آن متد روی همه مقادیر آرایه زیر تکرار می شود و آن مقادیر را جمع می کند و مقدار مناسب را برمی گرداند. اگرچه باید مراقب باشیم زیرا فراخوانی متد sum() می تواند بسیار پرهزینه باشد:
        //then
        executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
        executorService.shutdown();

        assertEquals(counter.sum(), numberOfIncrements * numberOfThreads);
        assertEquals(counter.sum(), numberOfIncrements * numberOfThreads);
    }

    @Test
    public void givenMultipleThread_whenTheyWriteToSharedLongAdder_thenShouldCalculateSumForThemAndResetAdderAfterward() throws InterruptedException {
        //given
        LongAdder counter = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        int numberOfThreads = 4;
        int numberOfIncrements = 100;

        //when
        Runnable incrementAction = () -> IntStream
          .range(0, numberOfIncrements)
          .forEach((i) -> counter.increment());

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(incrementAction);
        }

        //then
        executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
        executorService.shutdown();
//گاهی اوقات، پس از فراخوانی sum()، می‌خواهیم تمام حالت‌های مرتبط با نمونه LongAdder را پاک کنیم و از ابتدا شروع به شمارش کنیم. برای رسیدن به این هدف می توانیم از متد sumThenReset استفاده کنیم:
        assertEquals(counter.sumThenReset(), numberOfIncrements * numberOfThreads);
//توجه داشته باشید که فراخوانی بعدی متد sum() صفر را برمی گرداند به این معنی که حالت با موفقیت تنظیم مجدد شد.
//        علاوه بر این، جاوا DoubleAdder را نیز فراهم می کند تا مجموع مقادیر دوگانه را با API مشابه LongAdder حفظ کند.
        await().until(() -> assertEquals(counter.sum(), 0));
    }
}
