package com.example.concurrent.countdownlatch;

import com.example.concurrent.countDownLatch.CountdownLatchCountExample;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CountdownLatchCountExampleUnitTest {
//وقتی صحبت از همزمانی به میان می‌آید، مفهوم‌سازی آنچه که هر کدام برای انجام آن در نظر گرفته شده‌اند می‌تواند چالش برانگیز باشد.
//
//اول از همه، هر دو CountDownLatch و CyclicBarrier برای مدیریت برنامه های چند رشته ای استفاده می شوند.
//
//و، هر دوی آنها برای بیان چگونگی انتظار یک رشته یا گروهی از موضوعات در نظر گرفته شده اند.

//    CountDownLatch ساختاری است که یک thread روی آن منتظر می ماند در حالی که رشته های دیگر روی چفت شمارش معکوس می کنند تا به صفر برسد.
//
//ما می توانیم این را مانند یک غذا در رستورانی که در حال آماده شدن است فکر کنیم. مهم نیست که کدام آشپز هر تعداد از n مورد را آماده کند، پیشخدمت باید منتظر بماند تا همه اقلام در بشقاب قرار گیرند. اگر یک بشقاب n مورد مصرف کند، هر آشپزی برای هر اقلامی که در بشقاب می گذارد روی چفت شمارش معکوس می کند.

//    بیایید نگاهی عمیق‌تر به برخی از تفاوت‌های معنایی بین این دو کلاس داشته باشیم.
//
//همانطور که در تعاریف بیان شد، CyclicBarrier به تعدادی از رشته ها اجازه می دهد تا روی یکدیگر منتظر بمانند، در حالی که CountDownLatch به یک یا چند رشته اجازه می دهد تا برای تکمیل تعدادی از کارها منتظر بمانند.
//
//به طور خلاصه، CyclicBarrier تعداد رشته‌ها را حفظ می‌کند در حالی که CountDownLatch تعداد وظایف را حفظ می‌کند.
//
//در کد زیر یک CountDownLatch با تعداد دو تعریف می کنیم. در مرحله بعد، countDown() را دو بار از یک رشته واحد فراخوانی می کنیم:
    @Test
    public void whenCountDownLatch_completed() {
        CountdownLatchCountExample ex = new CountdownLatchCountExample(2);
        boolean isCompleted = ex.callTwiceInSameThread();
        assertTrue(isCompleted);
    }
}
