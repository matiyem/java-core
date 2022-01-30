package com.example;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is defined as a manual test because it tries to simulate the race conditions
 * in a concurrent program that is poorly designed and hence may fail nondeterministically.
 * This will help the CI jobs to ignore these tests and a developer to run them manually.
 *
 */
public class MyCounterTempusFugitManualTest {
//	کتابخانه جاوا tempus-fugit به ما کمک می کند تا کدهای همزمان را به راحتی بنویسیم و آزمایش کنیم. ما فقط بر روی بخش آزمایشی این کتابخانه در اینجا تمرکز خواهیم کرد. قبلاً دیدیم که ایجاد استرس روی کد با موضوعات متعدد، شانس یافتن نقص‌های مربوط به همزمانی را افزایش می‌دهد.
//
//در حالی که ما می‌توانیم برنامه‌های کاربردی بنویسیم تا خودمان استرس ایجاد کنیم، tempus-fugit راه‌های مناسبی برای رسیدن به همان استرس ارائه می‌کند.
//
//بیایید دوباره همان کدی را که قبلاً برای ایجاد استرس تلاش کردیم، مرور کنیم و بفهمیم که چگونه می‌توانیم با استفاده از tempus-fugit به همان چیزی برسیم:
//	در اینجا، ما از دو مورد از قوانین موجود در tempus-fugit استفاده می کنیم. این قوانین تست ها را قطع می کنند و به ما کمک می کنند تا رفتارهای مورد نظر مانند تکرار و همزمانی را اعمال کنیم. بنابراین، به طور موثر، ما عملیات تحت آزمایش را ده بار از ده رشته مختلف تکرار می کنیم.
//
//با افزایش تکرار و همزمانی، شانس ما برای تشخیص عیوب مربوط به همزمانی افزایش می یابد.

	@Rule
	public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule
	public RepeatingRule rule = new RepeatingRule();

	private static MyCounter counter = new MyCounter();

	@Test
	@Concurrent(count = 2)
	@Repeating(repetition = 10)
	public void runsMultipleTimes() {
		counter.increment();
	}

	@AfterClass
	public static void annotatedTestRunsMultipleTimes() throws InterruptedException {
		assertEquals(counter.getCount(), 20);
	}

}
