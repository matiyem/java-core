package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

/**
 * created by Atiye Mousavi
 * Date: 12/17/2021
 * Time: 1:25 PM
 **/


public class InfiniteStreams {
    private static final Logger LOG = LoggerFactory.getLogger(InfiniteStreams.class);

    public static void main(String[] args) {

    }
    private static void doWhileOldWay() {

        int i = 0;
        while (i < 10) {
            LOG.debug("{}", i);
            i++;
        }
    }
    private static void doWhileStreamWay(){
        Stream<Integer> integers=Stream.iterate(0,i->i+1);
        integers.limit(10).forEach(System.out::println);
    }
}
