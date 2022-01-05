package com.example.primitiveStreams;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PrimitiveStreamsUnitTest {
    //valueAble استریم ها را نیاز به بستن نیست زیرا توسط garbage collector جمع میشود
    //برای io stream ها باید ببندیم زیرا با باز کردن یک io stream یک file channel باز میشود که این با بسته شدن stream آن هم بسته میشود که اگر این کار نکنیم باعث leak شدن منابع میشود
    // امکان add و remove به استریم وجود ندارد

    private PrimitiveStreams streams = new PrimitiveStreams();

    @Test
    public void givenAnArrayOfIntegersWhenMinIsCalledThenCorrectMinIsReturned() {
        int[] integers = new int[] { 20, 98, 12, 7, 35 };
        int min = streams.min(integers); // returns 7

        assertEquals(7, min);
    }

    @Test
    public void givenAnArrayOfIntegersWhenMaxIsCalledThenCorrectMaxIsReturned() {
        int max = streams.max(20, 98, 12, 7, 35);

        assertEquals(98, max);
    }

    @Test
    public void givenAnArrayOfIntegersWhenSumIsCalledThenCorrectSumIsReturned() {
        int sum = streams.sum(20, 98, 12, 7, 35);

        assertEquals(172, sum);
    }

    @Test
    public void givenAnArrayOfIntegersWhenAvgIsCalledThenCorrectAvgIsReturned() {
        double avg = streams.avg(20, 98, 12, 7, 35);

        assertTrue(34.4 == avg);
    }

    @Test
    public void givenARangeOfIntegersWhenIntStreamSumIsCalledThenCorrectSumIsReturned() {
        //range عدد آخر را شامل نمیشود
        int sum = IntStream.range(1, 10).sum();

        assertEquals(45, sum);
    }

    @Test
    public void givenARangeClosedOfIntegersWhenIntStreamSumIsCalledThenCorrectSumIsReturned() {
        //rangeClose مقدار انتهایی را هم شامل میشود ولی range مقدار انتهایی شامل نمیشود
        int sum = IntStream.rangeClosed(1, 10).sum();

        assertEquals(55, sum);
    }

    @Test
    public void givenARangeWhenForEachIsCalledThenTheIndicesWillBePrinted() {
        //rangeClose مقدار انتهایی را هم شامل میشود ولی range مقدار انتهایی شامل نمیشود
        IntStream.rangeClosed(1, 5).parallel().forEach(System.out::println);
    }

    @Test
    public void givenAnArrayWhenSumIsCalledThenTheCorrectSumIsReturned() {
        //برای تبدیل wrapper به primitive از mapToInt استفاده میشود
        //همیشه از mapToxx و flatMapToxx برای تبدیل به primitive استفاده میشود
        int sum = Stream.of(33, 45).mapToInt(i -> i).sum();

        assertEquals(78, sum);
    }

    @Test
    public void givenAnIntStreamThenGetTheEvenIntegers() {
        //rangeClose مقدار انتهایی را هم شامل میشود ولی range مقدار انتهایی شامل نمیشود
        //از box برای تبدیل primitive به wrapper استفاده میشود
        List<Integer> evenInts = IntStream.rangeClosed(1, 10).filter(i -> i % 2 == 0).boxed().collect(Collectors.toList());

        List<Integer> expected = IntStream.of(2, 4, 6, 8, 10).boxed().collect(Collectors.toList());

        assertEquals(expected, evenInts);
    }

    class Person {
        private int age;

        Person(int age) {
            this.age = age;
        }

        int getAge() {
            return age;
        }
    }
}