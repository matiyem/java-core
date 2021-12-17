package com.example;

import java.util.List;
import java.util.stream.Stream;

/**
 * created by Atiye Mousavi
 * Date: 12/17/2021
 * Time: 2:34 PM
 **/


public class StreamApi {
    public static String getLastElementUsingReduce(List<String> valueList) {
        Stream<String> stream = valueList.stream();
        return stream.reduce((first, second) -> second).orElse(null);
    }

    public static Integer getInfiniteStreamLastElementUsingReduce() {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
        return stream.limit(20).reduce((first,second)->second).orElse(null);
    }
    public static String getLastElementUsingSkip(List<String> valueList){
        long count=(long) valueList.size();
        Stream<String> stream=valueList.stream();
        return stream.skip(count -1).findFirst().orElse(null);
    }
}
