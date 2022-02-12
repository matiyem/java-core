package com.example.concurrent.cyclicbarrier;

import com.example.concurrent.cyclicBarrier.CyclicBarrierCountExample;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class CyclicBarrierCountExampleUnitTest {
//وقتی صحبت از همزمانی به میان می‌آید، مفهوم‌سازی آنچه که هر کدام برای انجام آن در نظر گرفته شده‌اند می‌تواند چالش برانگیز باشد.
//
//اول از همه، هر دو CountDownLatch و CyclicBarrier برای مدیریت برنامه های چند رشته ای استفاده می شوند.
//
//و، هر دوی آنها برای بیان چگونگی انتظار یک رشته یا گروهی از موضوعات در نظر گرفته شده اند.

//    CyclicBarrier یک ساختار قابل استفاده مجدد است که در آن گروهی از thread ها با هم منتظر می مانند تا تمام نخ ها وارد شوند. در آن نقطه، مانع شکسته می شود و به صورت اختیاری می توان اقدامی انجام داد.
//
//ما می توانیم مانند یک گروه از دوستان به این موضوع فکر کنیم. هر بار که آنها قصد دارند در یک رستوران غذا بخورند، یک نقطه مشترک را تعیین می کنند که در آن می توانند ملاقات کنند. آنها در آنجا منتظر یکدیگر هستند و تنها زمانی که همه از راه می رسند می توانند به رستوران بروند تا با هم غذا بخورند.

//    بیایید نگاهی عمیق‌تر به برخی از تفاوت‌های معنایی بین این دو کلاس داشته باشیم.
//
//همانطور که در تعاریف بیان شد، CyclicBarrier به تعدادی از رشته ها اجازه می دهد تا روی یکدیگر منتظر بمانند، در حالی که CountDownLatch به یک یا چند رشته اجازه می دهد تا برای تکمیل تعدادی از کارها منتظر بمانند.
//
//به طور خلاصه، CyclicBarrier تعداد رشته‌ها را حفظ می‌کند در حالی که CountDownLatch تعداد وظایف را حفظ می‌کند.
//
//    هنگامی که قفل به صفر رسید، تماس انتظار برمی گردد.
//
//توجه داشته باشید که در این مورد، ما توانستیم همان موضوع را دو بار کاهش دهیم.
//
//با این حال، CyclicBarrier در این مورد متفاوت است.
//
    @Test
    public void whenCyclicBarrier_notCompleted() {
        CyclicBarrierCountExample ex = new CyclicBarrierCountExample(2);
        boolean isCompleted = ex.callTwiceInSameThread();
        assertFalse(isCompleted);
    }
}
