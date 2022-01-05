package com.example.primitiveStreams;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Create by Atiye Mousavi
 * Date: 1/4/2022
 * Time: 10:08 AM
 **/
class PrimitiveStreams {
    int min(int[] integers){
        return Arrays.stream(integers).min().getAsInt();
    }
    int max(int...integers){
        return IntStream.of(integers).max().getAsInt();
    }
    int sum(int...integers){
        return IntStream.of(integers).sum();
    }
    double avg(int ...integers){
        return IntStream.of(integers).average().getAsDouble();
    }
}
