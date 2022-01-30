package com.example;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;
import org.junit.Test;

/**
 * This is defined as a manual test because it tries to simulate the race conditions
 * in a concurrent program that is poorly designed and hence may fail nondeterministically.
 * This will help the CI jobs to ignore these tests and a developer to run them manually.
 *
 */
public class MyCounterMultithreadedTCManualTest extends MultithreadedTestCase {
//    MultithreadedTC یک چارچوب دیگر برای آزمایش برنامه های همزمان است. دارای مترونومی است که برای کنترل دقیق توالی فعالیت ها در رشته های متعدد استفاده می شود. از موارد آزمایشی پشتیبانی می‌کند که برهم‌آمیزی خاصی از رشته‌ها را اعمال می‌کنند. از این رو، در حالت ایده‌آل باید بتوانیم هر درهم آمیختگی مهم را در یک رشته جداگانه به طور قطعی آزمایش کنیم.
//
//اکنون، معرفی کامل این کتابخانه غنی از ویژگی ها خارج از محدوده این آموزش است. اما، مطمئناً می‌توانیم ببینیم که چگونه می‌توان به سرعت تست‌هایی را راه‌اندازی کرد که ترکیب‌های احتمالی بین رشته‌های اجرایی را در اختیار ما قرار می‌دهند.
//
//بیایید ببینیم چگونه می توانیم کد خود را به طور قطعی تر با MultithreadedTC آزمایش کنیم:
//    در اینجا، ما دو رشته را برای کار بر روی شمارنده مشترک و افزایش آن تنظیم می کنیم. ما MultithreadedTC را به گونه‌ای پیکربندی کرده‌ایم که این آزمایش را با این رشته‌ها برای حداکثر هزاران interleaving مختلف اجرا کند تا زمانی که یکی را شناسایی کند که شکست خورده است.

    private MyCounter counter;

    @Override
    public void initialize() {
        counter = new MyCounter();
    }

    public void thread1() throws InterruptedException {
        counter.increment();
    }

    public void thread2() throws InterruptedException {
        counter.increment();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void finish() {
    	assertEquals(2, counter.getCount());
    }

    @Test
    public void testCounter() throws Throwable {
        TestFramework.runManyTimes(new MyCounterMultithreadedTCManualTest(), 1000);
    }
}
