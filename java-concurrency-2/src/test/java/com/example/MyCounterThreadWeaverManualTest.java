package com.example;

import com.google.testing.threadtester.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is defined as a manual test because it tries to simulate the race conditions
 * in a concurrent program that is poorly designed and hence may fail nondeterministically.
 * This will help the CI jobs to ignore these tests and a developer to run them manually.
 *
 */
public class MyCounterThreadWeaverManualTest {
//	Thread Weaver در اصل یک چارچوب جاوا برای آزمایش کدهای چند رشته ای است. قبلاً دیده بودیم که درهم آمیختن رشته ها کاملاً غیرقابل پیش بینی است، و از این رو، ممکن است هیچ گاه از طریق آزمایش های منظم نقص های خاصی را پیدا نکنیم. چیزی که ما به طور موثر به آن نیاز داریم راهی برای کنترل interleave ها و آزمایش همه interleaving های ممکن است. در تلاش قبلی ما ثابت شده است که این یک کار کاملاً پیچیده است.
//
//بیایید ببینیم که چگونه Thread Weaver می تواند در اینجا به ما کمک کند. Thread Weaver به ما این امکان را می‌دهد که اجرای دو رشته مجزا را به روش‌های زیادی، بدون نگرانی در مورد نحوه انجام آن، به هم متصل کنیم. همچنین این امکان را به ما می دهد که کنترل دقیقی بر نحوه ی به هم زدن رشته ها داشته باشیم.
//
//بیایید ببینیم چگونه می‌توانیم تلاش ساده‌لوحانه قبلی خود را بهبود بخشیم:
//	در اینجا، ما دو رشته را تعریف کرده‌ایم که سعی می‌کنند شمارنده ما را افزایش دهند. Thread Weaver سعی خواهد کرد که این تست را با این رشته ها در تمام سناریوهای در هم آمیختگی ممکن اجرا کند. احتمالاً در یکی از interleave ها، نقصی را دریافت می کنیم که در کد ما کاملاً مشهود است.

	private MyCounter counter;

	@ThreadedBefore
	public void before() {
		counter = new MyCounter();
	}

	@ThreadedMain
	public void mainThread() {
		counter.increment();
	}

	@ThreadedSecondary
	public void secondThread() {
		counter.increment();
	}

	@ThreadedAfter
	public void after() {
		assertEquals(2, counter.getCount());
	}

	@Test
	public void testCounter() {
		new AnnotatedTestRunner().runTests(this.getClass(), MyCounter.class);
	}

}