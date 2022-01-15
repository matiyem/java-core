package com.example.containsPerformance;

import com.example.arrayListVsVector.Employee;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
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
    Time: 2:56 PM
**/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5)
public class CollectionsBenchmark {

    @State(Scope.Thread)
    public static class MyState {
        private Set<Employee> employeeSet = new HashSet<>();
        private List<Employee> employeeList = new ArrayList<>();

        private long iterations = 10_000;

        private Employee employee = new Employee(100L, "Harry");

        @Setup(Level.Trial)
        public void setUp() {

            for (long i = 0; i < iterations; i++) {
                employeeSet.add(new Employee(i,"John"));
                employeeList.add(new Employee(i,"John"));
            }
            employeeList.add(employee);
            employeeSet.add(employee);
        }
        @Benchmark
        public boolean testArrayList(MyState state){
            return state.employeeList.contains(state.employee);
        }
        @Benchmark
        public boolean testHashSet(MyState state){
            return state.employeeSet.contains(state.employee);
        }

        public static void main(String[] args) throws RunnerException {
            Options options=new OptionsBuilder()
                    .include(CollectionsBenchmark.class.getSimpleName()).threads(1)
                    .forks(1).shouldFailOnError(true)
                    .shouldDoGC(true)
                    .jvmArgs("-server").build();
            new Runner(options).run();
        }
    }
}
