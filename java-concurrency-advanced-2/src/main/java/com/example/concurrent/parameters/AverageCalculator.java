package com.example.concurrent.parameters;

import java.util.concurrent.Callable;
import java.util.stream.IntStream;

/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 2:49 PM
 **/


public class AverageCalculator implements Callable<Double> {
    int[] numbers;

    public AverageCalculator(int... numbers) {
        this.numbers = numbers == null ? new int[0] : numbers;
    }

    @Override
    public Double call() throws Exception {
        return IntStream.of(this.numbers).average().orElse(0d);
    }
}
