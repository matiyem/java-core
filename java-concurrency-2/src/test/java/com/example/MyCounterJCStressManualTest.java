package com.example;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;

import static org.openjdk.jcstress.annotations.Expect.ACCEPTABLE;
import static org.openjdk.jcstress.annotations.Expect.ACCEPTABLE_INTERESTING;

/**
 * This is defined as a manual test because it tries to simulate the race conditions
 * in a concurrent program that is poorly designed and hence may fail nondeterministically.
 * This will help the CI jobs to ignore these tests and a developer to run them manually.
 *
 */
@JCStressTest
@Outcome(id = "1", expect = ACCEPTABLE_INTERESTING, desc = "One update lost.")
@Outcome(id = "2", expect = ACCEPTABLE, desc = "Both updates.")
@State
public class MyCounterJCStressManualTest {
//    OpenJDK پروژه Code Tool را برای ارائه ابزارهای توسعه دهنده برای کار بر روی پروژه های OpenJDK حفظ می کند. چندین ابزار مفید تحت این پروژه وجود دارد، از جمله تست استرس همزمان جاوا (jcstress). این به عنوان یک مهار آزمایشی و مجموعه ای از تست ها برای بررسی صحت پشتیبانی همزمان در جاوا در حال توسعه است.
//
//اگرچه این یک ابزار آزمایشی است، اما هنوز هم می‌توانیم از آن برای تجزیه و تحلیل کدهای همزمان و نوشتن تست‌هایی برای تامین مالی نقص‌های مربوط به آن استفاده کنیم. بیایید ببینیم چگونه می‌توانیم کدی را که تاکنون در این آموزش استفاده کرده‌ایم، آزمایش کنیم. این مفهوم از منظر استفاده بسیار شبیه است:
//    OpenJDK پروژه Code Tool را برای ارائه ابزارهای توسعه دهنده برای کار بر روی پروژه های OpenJDK حفظ می کند. چندین ابزار مفید تحت این پروژه وجود دارد، از جمله تست استرس همزمان جاوا (jcstress). این به عنوان یک مهار آزمایشی و مجموعه ای از تست ها برای بررسی صحت پشتیبانی همزمان در جاوا در حال توسعه است.
//
//اگرچه این یک ابزار آزمایشی است، اما هنوز هم می‌توانیم از آن برای تجزیه و تحلیل کدهای همزمان و نوشتن تست‌هایی برای تامین مالی نقص‌های مربوط به آن استفاده کنیم. بیایید ببینیم چگونه می‌توانیم کدی را که تاکنون در این آموزش استفاده کرده‌ایم، آزمایش کنیم. این مفهوم از منظر استفاده بسیار شبیه است:

    private MyCounter counter;

    @Actor
    public void actor1() {
        counter.increment();
    }

    @Actor
    public void actor2() {
        counter.increment();
    }

    @Arbiter
    public void arbiter(I_Result r) {
        r.r1 = counter.getCount();
    }

}
