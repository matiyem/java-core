package com.example.concurrent.accumulator;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

import static junit.framework.TestCase.assertEquals;

public class LongAccumulatorUnitTest {
//    LongAccumulator همچنین یک کلاس بسیار جالب است - که به ما امکان می دهد یک الگوریتم بدون قفل را در تعدادی از سناریوها پیاده سازی کنیم. به عنوان مثال، می‌توان از آن برای جمع‌آوری نتایج مطابق LongBinaryOperator ارائه‌شده استفاده کرد – این عملکرد مشابه عملیات ()reduce از Stream API است.
//
//نمونه LongAccumulator را می توان با ارائه LongBinaryOperator و مقدار اولیه سازنده آن ایجاد کرد. نکته مهمی که باید به خاطر داشته باشید این است که LongAccumulator به درستی کار خواهد کرد اگر ما آن را با یک تابع جابجایی که ترتیب انباشته شدن آن مهم نیست، ارائه کنیم.

    @Test
    public void givenLongAccumulator_whenApplyActionOnItFromMultipleThrads_thenShouldProduceProperResult() throws InterruptedException {
//ما در حال ایجاد LongAccumulator هستیم که یک مقدار جدید به مقداری که قبلاً در انباشته بود اضافه می کند. ما مقدار اولیه LongAccumulator را صفر می‌کنیم، بنابراین در اولین فراخوانی متد ()accumulate، مقدار previousValue مقدار صفر خواهد داشت.
//
//بیایید متد accumulate() را از چندین رشته فراخوانی کنیم:

        // given
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        LongBinaryOperator sum = Long::sum;
        LongAccumulator accumulator = new LongAccumulator(sum, 0L);
        int numberOfThreads = 4;
        int numberOfIncrements = 100;

        // when
        Runnable accumulateAction = () -> IntStream.rangeClosed(0, numberOfIncrements).forEach(accumulator::accumulate);
//توجه کنید که چگونه یک عدد را به عنوان آرگومان به متد accumulate() ارسال می کنیم. آن متد تابع sum() ما را فراخوانی می کند.
//
//LongAccumulator از پیاده سازی مقایسه و تعویض استفاده می کند - که منجر به این معناشناسی جالب می شود.
//
//ابتدا یک عمل تعریف شده به عنوان LongBinaryOperator را اجرا می کند و سپس بررسی می کند که آیا مقدار قبلی تغییر کرده است یا خیر. اگر تغییر کرد، عمل دوباره با مقدار جدید اجرا می شود. اگر نه، موفق می شود مقدار ذخیره شده در انباشته را تغییر دهد.
//
//اکنون می توانیم ادعا کنیم که مجموع همه مقادیر از همه تکرارها 20200 بوده است:
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(accumulateAction);
        }

        // then
        executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
        executorService.shutdown();
        assertEquals(accumulator.get(), 20200);

    }
}
