package com.example.workStealing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/*
    Create by Atiye Mousavi 
    Date: 2/16/2022
    Time: 10:40 AM
**/
public class PrimeNumbers extends RecursiveAction {
//    یافتن اعداد اول از مجموعه‌ای از اعداد می‌تواند فرآیند محاسباتی پرهزینه‌ای باشد. این عمدتا به دلیل اندازه مجموعه اعداد است.
    private int lowerBound;
    private int upperBound;
    private int granularity;
    public static final List<Integer> GRANULARITIES = Arrays.asList(1, 10, 100, 1000, 10000);
    private AtomicInteger noOfPrimeNumbers;

    public PrimeNumbers(int lowerBound, int upperBound, int granularity, AtomicInteger noOfPrimeNumbers) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.granularity = granularity;
        this.noOfPrimeNumbers = noOfPrimeNumbers;
    }

    public PrimeNumbers(int upperBound) {
        this.upperBound = upperBound;
    }

    PrimeNumbers(int lowerBound, int upperBound, AtomicInteger noOfPrimeNumbers) {
        this(lowerBound, upperBound, 100, noOfPrimeNumbers);
    }

    private List<PrimeNumbers> subTasks() {
        List<PrimeNumbers> subTasks = new ArrayList<>();

        for (int i = 1; i <= this.upperBound / granularity; i++) {
            int upper = i * granularity;
            int lower = (upper - granularity) + 1;
            subTasks.add(new PrimeNumbers(lower, upper, noOfPrimeNumbers));
        }
        return subTasks;
    }

    @Override
    protected void compute() {
        if (((upperBound + 1) - lowerBound) > granularity) {
            ForkJoinTask.invokeAll(subTasks());
        } else {
            findPrimeNumbers();
        }
    }

    public void findPrimeNumbers() {
        for (int num = lowerBound; num <= upperBound; num++) {
            if (isPrime(num)) {
                noOfPrimeNumbers.getAndIncrement();
            }
        }
    }

    private boolean isPrime(int number) {
        if (number == 2) {
            return true;
        }
        if (number == 1 || number % 2 == 0) {
            return false;
        }
        int noOfNaturalNumbers = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                noOfNaturalNumbers++;
            }
        }
        return noOfNaturalNumbers == 2;
    }

    public int noOfPrimeNumbers() {
        return noOfPrimeNumbers.intValue();
    }
}
