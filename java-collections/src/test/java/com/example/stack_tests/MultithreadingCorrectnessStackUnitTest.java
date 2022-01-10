package com.example.stack_tests;

import com.example.thread_safe_lifo.DequeBasedSynchronizedStack;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.concurrent.ConcurrentLinkedDeque;

import static java.util.stream.IntStream.range;


public class MultithreadingCorrectnessStackUnitTest {
//    در نهایت، چند نکته دیگر که ارزش درک و یادآوری را در مورد این پیاده سازی خاص دارند:
//
//این برای thread ایمن نیست
//عناصر null پذیرفته نمی شوند
//به طور قابل توجهی سریعتر از stack همگام شده کار می کند
//به دلیل محل ارجاع بهتر، یک صف سریعتر از LinkedList است
//اکثر عملیات دارای پیچیدگی زمانی ثابت مستهلک شده اند
//یک Iterator که توسط یک ArrayDeque برگردانده می شود، سریعاً شکست می خورد
//ArrayDeque به طور خودکار اندازه یک آرایه را دوبرابر می کند زمانی که نشانگر سر و دم با یکدیگر ملاقات می کنند در حالی که یک عنصر را اضافه می کنند.

    @Test
    public void givenSynchronizedDeque_whenExecutedParallel_thenWorkRight() {

        DequeBasedSynchronizedStack<Integer> deque = new DequeBasedSynchronizedStack<>();

        // Serial execution of push on ConcurrentLinkedQueue will always result in correct execution.
        range(1, 10000).forEach(value -> deque.push(value));

        int sum = 0;
//        عنصر را در جلوی صف بررسی می کند، بدون اینکه آن را حذف کندpeek
        while(deque.peek() != null) {
            sum += deque.pop();
        }

        Assert.assertEquals(49995000, sum);

        // Parallel execution of push on ConcurrentLinkedQueue will always result in correct execution.
        range(1, 10000).parallel().forEach(value -> deque.push(value));

        sum = 0;
        while(deque.peek() != null) {
            sum += deque.pop();
        }

        Assert.assertEquals(49995000, sum);
    }

    @Test
    public void givenConcurrentLinkedQueue_whenExecutedParallel_thenWorkRight() {

        ConcurrentLinkedDeque<Integer> deque = new ConcurrentLinkedDeque<>();

        // Serial execution of push on ConcurrentLinkedQueue will always result in correct execution.
        range(1, 10000).forEach(value -> deque.push(value));

        int sum = 0;
        while(deque.peek() != null) {
            sum += deque.pop();
        }

        Assert.assertEquals(49995000, sum);

        // Parallel execution of push on ConcurrentLinkedQueue will always result in correct execution.
        range(1, 10000).parallel().forEach(value -> deque.push(value));

        sum = 0;
        while(deque.peek() != null) {
            sum += deque.pop();
        }

        Assert.assertEquals(49995000, sum);
    }

    @Test
    public void givenArrayDeque_whenExecutedParallel_thenShouldFail() {
//در زیر هود، ArrayDeque توسط آرایه ای پشتیبانی می شود که با پر شدن اندازه آن دو برابر می شود.
//
//در ابتدا، آرایه با اندازه 16 مقداردهی اولیه می‌شود. این آرایه به‌عنوان یک صف دو طرفه پیاده‌سازی می‌شود که در آن دو نشانگر یعنی یک سر و یک دم را حفظ می‌کند.
//

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // Serial execution of push on ArrayDeque will always result in correct execution.
        range(1, 10000).forEach(value -> deque.push(value));

        int sum = 0;
        while(deque.peek() != null) {
            sum += deque.pop();
        }

        Assert.assertEquals(49995000, sum);

        // Parallel execution of push on ArrayDeque will not result in correct execution.
        range(1, 10000).parallel().forEach(value -> deque.push(value));

        sum = 0;
        while(deque.peek() != null) {
            sum += deque.pop();
        }

        // This shouldn't happen.
        if(sum == 49995000) {
            System.out.println("Something wrong in the environment, Please try some big value and check");
            // To safe-guard build without test failures.
            return;
        }

        Assert.assertNotEquals(49995000, sum);
    }
}
