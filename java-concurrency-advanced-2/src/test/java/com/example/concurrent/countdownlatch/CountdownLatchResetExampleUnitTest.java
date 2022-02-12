package com.example.concurrent.countdownlatch;

import com.example.concurrent.countDownLatch.CountdownLatchResetExample;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CountdownLatchResetExampleUnitTest {
    
    @Test
    public void whenCountDownLatch_noReset() {
//دومین تفاوت آشکار بین این دو کلاس قابلیت استفاده مجدد است. برای توضیح بیشتر، هنگامی که مانع در CyclicBarrier حرکت می کند، تعداد به مقدار اولیه خود باز می گردد. CountDownLatch متفاوت است زیرا شمارش هرگز بازنشانی نمی شود.
//
//در کد داده شده، یک CountDownLatch با تعداد 7 تعریف می کنیم و آن را از طریق 20 تماس مختلف شمارش می کنیم:
//        مشاهده می کنیم که حتی اگر 20 رشته مختلف countDown() را فراخوانی کنند، پس از رسیدن به صفر، شمارش بازنشانی نمی شود.
        CountdownLatchResetExample ex = new CountdownLatchResetExample(7,20);
        int lineCount = ex.countWaits();
        assertTrue(lineCount <= 7);
    }
}
