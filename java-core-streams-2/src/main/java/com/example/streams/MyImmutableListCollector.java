package com.example.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * created by Atiye Mousavi
 * Date: 12/24/2021
 * Time: 7:04 PM
 **/


public class MyImmutableListCollector {
    public static <T,A extends List<T>>Collector<T,A,List<T>> toImmutableList(Supplier<A> supplier){
        return Collector.of(supplier, List::add, (left, right) -> {
            left.addAll(right);
            return left;
        }, Collections::unmodifiableList);
    }
    public static <T> Collector<T,List<T>,List<T>> toImmutableList(){
        return toImmutableList(ArrayList::new);
    }
}
