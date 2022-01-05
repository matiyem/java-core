package com.example.parallel;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Create by Atiye Mousavi
 * Date: 1/4/2022
 * Time: 10:02 AM
 **/
public class SplittingCosts {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static void sourceSplittingIntStreamSequential(){
        //rangeClose مقدار انتهایی را هم شامل میشود ولی range مقدار انتهایی شامل نمیشود
        IntStream.rangeClosed(1,100).reduce(0,Integer::sum);
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static void sourceSplittingIntStreamParallel(){
        //rangeClose مقدار انتهایی را هم شامل میشود ولی range مقدار انتهایی شامل نمیشود
        IntStream.rangeClosed(1,100).parallel().reduce(0,Integer::sum);
    }
}
