package com.example.concurrent.phaser;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

import static junit.framework.TestCase.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PhaserUnitTest {
//    در این مقاله، ساختار Phaser از بسته java.util.concurrent را بررسی خواهیم کرد. این ساختار بسیار شبیه به CountDownLatch است که به ما اجازه می‌دهد تا اجرای رشته‌ها را هماهنگ کنیم. در مقایسه با CountDownLatch، دارای برخی عملکردهای اضافی است.
//
//Phaser مانعی است که قبل از ادامه اجرا، تعداد پویای نخ ها باید روی آن منتظر بمانند. در CountDownLatch این عدد را نمی توان به صورت پویا پیکربندی کرد و باید هنگام ایجاد نمونه ارائه شود.

//    Phaser به ما اجازه می دهد تا منطقی بسازیم که در آن نخ ها باید قبل از رفتن به مرحله بعدی اجرا روی مانع منتظر بمانند.
//
//ما می‌توانیم چندین فاز اجرا را هماهنگ کنیم و از یک نمونه Phaser برای هر مرحله برنامه مجدد استفاده کنیم. هر فاز می‌تواند دارای تعداد متفاوتی از رشته‌ها باشد که منتظر پیشرفت به فاز دیگر هستند. در ادامه نمونه ای از استفاده از فازها را بررسی خواهیم کرد.
//
//برای شرکت در هماهنگی، موضوع باید خود را با نمونه Phaser ثبت () کند. توجه داشته باشید که این فقط تعداد طرف‌های ثبت‌شده را افزایش می‌دهد، و ما نمی‌توانیم بررسی کنیم که آیا رشته فعلی ثبت شده است یا خیر - باید پیاده‌سازی را برای پشتیبانی از آن زیر کلاس بندی کنیم.
//
//thread با فراخوانی arriveAndAwaitAdvance()، که یک روش مسدود کننده است، سیگنال می دهد که به مانع رسیده است. زمانی که تعداد مهمانی های وارد شده برابر با تعداد مهمانان ثبت نام شده باشد، اجرای برنامه ادامه می یابد و تعداد فاز افزایش می یابد. با فراخوانی متد getPhase() می توانیم شماره فاز فعلی را بدست آوریم.
//
//هنگامی که thread کار خود را به پایان رساند، باید متد arriveAndDeregister() را فراخوانی کنیم تا سیگنال فعلی را در این مرحله خاص به حساب نیاوریم.

    @Test
    public void givenPhaser_whenCoordinateWorksBetweenThreads_thenShouldCoordinateBetweenMultiplePhases() {
//        فاز بعد از مقداردهی اولیه برابر با صفر است.
//
//کلاس Phaser یک سازنده دارد که در آن می توانیم یک نمونه والد را به آن ارسال کنیم. در مواردی که ما تعداد زیادی طرف داریم که هزینه‌های هنگفتی برای همگام‌سازی را تجربه می‌کنند مفید است. در چنین شرایطی، نمونه هایی از فازرها ممکن است به گونه ای تنظیم شوند که گروه هایی از فازهای فرعی یک والد مشترک داشته باشند.
//
//در مرحله بعد، اجازه دهید سه رشته اقدام LongRunningAction را شروع کنیم، که روی مانع منتظر می مانند تا زمانی که متد arriveAndAwaitAdvance() را از رشته اصلی فراخوانی کنیم.
//
//به خاطر داشته باشید که ما Phaser خود را با 1 مقداردهی اولیه کرده ایم و سه بار دیگر register() را فراخوانی کرده ایم. اکنون، سه رشته عملیاتی اعلام کرده‌اند که به مانع رسیده‌اند، بنابراین یک فراخوان دیگر برای ()arriveAndAwaitAdvance مورد نیاز است - یکی از رشته اصلی:
        //given
        ExecutorService executorService = Executors.newCachedThreadPool();
        Phaser ph = new Phaser(1);
        assertEquals(0, ph.getPhase());

        //when
        executorService.submit(new LongRunningAction("thread-1", ph));
        executorService.submit(new LongRunningAction("thread-2", ph));
        executorService.submit(new LongRunningAction("thread-3", ph));
//پس از اتمام آن مرحله، متد getPhase() یک را برمی گرداند زیرا برنامه پردازش مرحله اول اجرا را به پایان رساند.
//
//فرض کنید که دو رشته باید مرحله بعدی پردازش را انجام دهند. ما می‌توانیم از Phaser برای رسیدن به این هدف استفاده کنیم، زیرا به ما امکان می‌دهد به صورت پویا تعداد رشته‌هایی را که باید روی مانع منتظر بمانند، پیکربندی کنیم. ما در حال شروع دو رشته جدید هستیم، اما تا زمانی که فراخوانی ()arriveAndAwaitAdvance از رشته اصلی (همانند مورد قبلی) اجرا نشود، اجرا نمی شود:
        //then
        ph.arriveAndAwaitAdvance();
        assertEquals(1, ph.getPhase());

        //and
        executorService.submit(new LongRunningAction("thread-4", ph));
        executorService.submit(new LongRunningAction("thread-5", ph));
        ph.arriveAndAwaitAdvance();
        assertEquals(2, ph.getPhase());


        ph.arriveAndDeregister();
    }
}
