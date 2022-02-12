package com.example.concurrent.daemon;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DaemonThreadUnitTest {
//جاوا دو نوع thread را ارائه می دهد: موضوعات کاربر و موضوعات daemon.
//
//موضوعات کاربر موضوعاتی با اولویت بالا هستند. JVM منتظر می ماند تا هر رشته کاربری کار خود را قبل از خاتمه کامل کند.
//
//از طرف دیگر، رشته های شبح، رشته هایی با اولویت پایین هستند که تنها نقش آنها ارائه خدمات به نخ های کاربر است.
//
//از آنجایی که thread‌های شبح برای سرویس‌دهی به رشته‌های کاربر و تنها در زمانی که نخ‌های کاربر در حال اجرا هستند مورد نیاز هستند، پس از اتمام اجرای تمام رشته‌های کاربر، از خروج JVM جلوگیری نمی‌کنند.
//
//به همین دلیل است که حلقه‌های بی‌نهایت، که معمولاً در رشته‌های شبح وجود دارند، مشکلی ایجاد نمی‌کنند، زیرا هر کدی، از جمله بلوک‌های نهایی، پس از اتمام اجرای تمام رشته‌های کاربر، اجرا نمی‌شوند. به همین دلیل، thread های دیمون برای کارهای I/O توصیه نمی شوند.
//
//با این حال، استثناهایی برای این قاعده وجود دارد. کد طراحی ضعیف در رشته های دیمون می تواند از خروج JVM جلوگیری کند. به عنوان مثال، فراخوانی ()Thread.join در یک thread در حال اجرا می تواند خاموش شدن برنامه را مسدود کند.
//    رشته‌های Daemon برای کارهای پشتیبانی پس‌زمینه مانند جمع‌آوری زباله، آزاد کردن حافظه اشیاء استفاده نشده و حذف ورودی‌های ناخواسته از حافظه پنهان مفید هستند. بیشتر رشته های JVM رشته های شبح هستند.
    @Test
//    @Ignore
    public void whenCallIsDaemon_thenCorrect() {
//        هر رشته ای وضعیت دیمون رشته ای را که آن را ایجاد کرده است به ارث می برد. از آنجایی که رشته اصلی یک رشته کاربر است، هر رشته ای که در روش اصلی ایجاد شود به طور پیش فرض یک نخ کاربر است.
        NewThread daemonThread = new NewThread();
        NewThread userThread = new NewThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
        userThread.start();

        assertTrue(daemonThread.isDaemon());
        assertFalse(userThread.isDaemon());
    }

//    @Test(expected = IllegalThreadStateException.class)
    @Test()
//    @Ignore
    public void givenUserThread_whenSetDaemonWhileRunning_thenIllegalThreadStateException() {
//        متد setDaemon() را فقط می توان پس از ایجاد شی Thread و شروع نشدن thread فراخوانی کرد. تلاش برای فراخوانی setDaemon() در حالی که یک رشته در حال اجرا است، یک IllegalThreadStateException ایجاد می کند:
        NewThread daemonThread = new NewThread();
        daemonThread.start();
        daemonThread.setDaemon(true);
    }
}
