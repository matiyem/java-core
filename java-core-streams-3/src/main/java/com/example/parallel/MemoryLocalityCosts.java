package com.example.parallel;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Create by Atiye Mousavi
 * Date: 1/3/2022
 * Time: 3:47 PM
 **/
public class MemoryLocalityCosts {
    private static final int[] intArray = new int[1_000_000];
    private static final Integer[] integerArray = new Integer[1_000_000];

    static {
        //rangeClose مقدار انتهایی را هم شامل میشود ولی range مقدار انتهایی شامل نمیشود
        IntStream.rangeClosed(1, 1_000_000).forEach(i -> {
            intArray[i - 1] = i;
            integerArray[i - 1] = i;
        });
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static void localityIntArraySequential() {
        Arrays.stream(intArray).reduce(0, Integer::sum);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static void localityIntArrayParallel() {
        Arrays.stream(intArray).parallel().reduce(0, Integer::sum);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static void localityIntegerArraySequential() {
        Arrays.stream(integerArray).parallel().reduce(0, Integer::sum);
    }
}
