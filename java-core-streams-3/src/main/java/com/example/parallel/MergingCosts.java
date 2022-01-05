package com.example.parallel;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Create by Atiye Mousavi
 * Date: 1/4/2022
 * Time: 9:42 AM
 **/
public class MergingCosts {
//    اوراکل یک مدل ساده ارائه کرد که می تواند به ما کمک کند تعیین کنیم که آیا موازی سازی می تواند عملکرد ما را افزایش دهد یا خیر. در مدل NQ، N نشان دهنده تعداد عناصر داده منبع است، در حالی که Q نشان دهنده مقدار محاسبه انجام شده در هر عنصر داده است.
//
//هرچه حاصلضرب N*Q بزرگتر باشد، احتمال افزایش عملکرد از موازی سازی بیشتر است. برای مسائل با یک Q بسیار کوچک، مانند جمع کردن اعداد، قانون کلی این است که N باید بزرگتر از 10000 باشد. با افزایش تعداد محاسبات، اندازه داده مورد نیاز برای افزایش عملکرد از موازی سازی کاهش می یابد.
    private static final List<Integer> arrayListOfNumbers=new ArrayList<>();

    static {
        //rangeClose مقدار انتهایی را هم شامل میشود ولی range مقدار انتهایی شامل نمیشود
        IntStream.rangeClosed(1,1_000_000).forEach(i->{
            arrayListOfNumbers.add(i);
        });
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static void mergingCostsSumSequential(){
        System.out.println("mergingCostsSumSequential");
        arrayListOfNumbers.stream().reduce(0,Integer::sum);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static void mergingCostsSumParallel(){
        System.out.println("mergingCostsSumParallel");
        arrayListOfNumbers.stream().parallel().reduce(0,Integer::sum);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static void mergingCostsGroupingSequential(){
        //با متد Collectors.toSet() استریم را به set تبدیل میکنیم
        System.out.println("mergingCostsGroupingSequential");

        arrayListOfNumbers.stream().collect(Collectors.toSet());
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static void mergingCostsGroupingParallel(){
        System.out.println("mergingCostsGroupingParallel");

        arrayListOfNumbers.stream().parallel().collect(Collectors.toSet());
    }

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);

    }


}
