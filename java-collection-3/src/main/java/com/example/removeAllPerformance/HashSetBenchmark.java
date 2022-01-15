package com.example.removeAllPerformance;

import com.example.containsPerformance.Employee;
import org.apache.commons.lang3.RandomStringUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/*
    Create by Atiye Mousavi 
    Date: 1/12/2022
    Time: 3:42 PM
**/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5)
public class HashSetBenchmark {
    @State(Scope.Thread)
    public static class MyState {
        private Set<Employee> employeeSet1 = new HashSet<>();
        private List<Employee> employeeList1 = new ArrayList<>();
        private Set<Employee> employeeSet2 = new HashSet<>();
        private List<Employee> employeeList2 = new ArrayList<>();
        private Set<Employee> employeeSet3 = new HashSet<>();
        private Set<Employee> employeeSet4 = new HashSet<>();

        private long set1Size = 60000;
        private long list1Size = 50000;
        private long set2Size = 50000;
        private long list2Size = 60000;
        private long set3Size = 50000;
        private long set4Size = 60000;

        @Setup(Level.Trial)
        public void setUp() {

            for (long i = 0; i < set1Size; i++) {
                employeeSet1.add(new Employee(i, RandomStringUtils.random(7, true, false)));
            }

            for (long i = 0; i < list1Size; i++) {
                employeeList1.add(new Employee(i, RandomStringUtils.random(7, true, false)));
            }

            for (long i = 0; i < set2Size; i++) {
                employeeSet2.add(new Employee(i, RandomStringUtils.random(7, true, false)));
            }

            for (long i = 0; i < list2Size; i++) {
                employeeList2.add(new Employee(i, RandomStringUtils.random(7, true, false)));
            }

            for (long i = 0; i < set3Size; i++) {
                employeeSet3.add(new Employee(i, RandomStringUtils.random(7, true, false)));
            }

            for (long i = 0; i < set4Size; i++) {
                employeeSet4.add(new Employee(i, RandomStringUtils.random(7, true, false)));
            }

        }

    }

    @Benchmark
    public boolean given_SizeOfHashsetGreaterThanSizeOfCollection_When_RemoveAllFromHashSet_Then_GoodPerformance(MyState state) {
        return state.employeeSet1.removeAll(state.employeeList1);
    }

    @Benchmark
    public boolean given_SizeOfHashsetSmallerThanSizeOfCollection_When_RemoveAllFromHashSet_Then_BadPerformance(MyState state) {
        return state.employeeSet2.removeAll(state.employeeList2);
    }

    @Benchmark
    public boolean given_SizeOfHashsetSmallerThanSizeOfAnotherHashSet_When_RemoveAllFromHashSet_Then_GoodPerformance(MyState state) {
        return state.employeeSet3.removeAll(state.employeeSet4);
    }

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder().include(HashSetBenchmark.class.getSimpleName())
                .threads(1)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-server")
                .build();
        new Runner(options).run();
    }
}
