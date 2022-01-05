package com.example.parallel;

import java.util.Arrays;
import java.util.List;

/**
 * Create by Atiye Mousavi
 * Date: 1/4/2022
 * Time: 9:49 AM
 **/
public class ParallelStream {
    public static void main(String[] args) {
        List<Integer> listOfNumber= Arrays.asList(1,2,3,4);
        listOfNumber.parallelStream().forEach(number ->
                System.out.println(number + " " + Thread.currentThread().getName()));
    }
}
