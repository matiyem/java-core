package com.example.parallel;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Create by Atiye Mousavi
 * Date: 1/3/2022
 * Time: 3:37 PM
 **/
public class DifferentSourceSplitting {
    private static final List<Integer> arrayListOfNumber=new ArrayList<>();
    private static final List<Integer> linkedListOfNumbers=new ArrayList<>();

    static {
        //rangeClose مقدار انتهایی را هم شامل میشود ولی range مقدار انتهایی شامل نمیشود
        IntStream.rangeClosed(1,1_000_000).forEach(i ->{
            arrayListOfNumber.add(i);
            linkedListOfNumbers.add(i);
        });
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static void diffrentSourceArrayListSequential(){
        arrayListOfNumber.stream().reduce(0,Integer::sum);

    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static void differentSourceArrayListParallel(){
        linkedListOfNumbers.parallelStream().reduce(0,Integer::sum);
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static void differentSourceLinkedListSequential(){
        linkedListOfNumbers.stream().reduce(0,Integer::sum);
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static void differentSourceLinkedListParallel(){
        linkedListOfNumbers.parallelStream().reduce(0,Integer::sum);

    }

}
