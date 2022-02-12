package com.example.threadLocalRandom;

import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 5:30 PM
 **/


@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class ThreadLocalRandomBenchMarker {
    private final Random random = new Random();

    @Benchmark
    public int randomValuesUsingRandom() {
        return random.nextInt();
    }

    @Benchmark
    public int randomValuesUsingThreadLocalRandom() {
        return ThreadLocalRandom
                .current()
                .nextInt();
    }

}