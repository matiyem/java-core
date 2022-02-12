package com.example.threadLocalRandom;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import com.google.common.collect.ImmutableList;


/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 5:30 PM
 **/


public class ThreadLocalRandomBenchMarkRunner {
    public static void main(String[] args) throws RunnerException {
        ChainedOptionsBuilder options=new OptionsBuilder().include(ThreadLocalRandomBenchMarkRunner.class.getSimpleName())
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-server");
        for (Integer i :ImmutableList.of(1,2,8,32)){
            new Runner(
                    options.threads(i).build()).run();
        }
    }
}
