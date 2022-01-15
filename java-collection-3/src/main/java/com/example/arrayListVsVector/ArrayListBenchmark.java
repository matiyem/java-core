package com.example.arrayListVsVector;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import org.openjdk.jmh.runner.options.Options;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/*
    Create by Atiye Mousavi 
    Date: 1/12/2022
    Time: 1:01 PM
**/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 10)
public class ArrayListBenchmark {

    @State(Scope.Thread)
    public static class MyState {
        List<Employee> employeeList = new ArrayList<>();
        Vector<Employee> employeeVector = new Vector<>();

        long iterations = 100_000;

        Employee employee = new Employee(100L, "Harry");

        int employeeIndex = -1;

        @Setup(Level.Trial)
        public void setUp() {
            for (long i = 0; i < iterations; i++) {
                employeeList.add(new Employee(i, "John"));
                employeeVector.add(new Employee(i, "John"));
            }
            employeeList.add(employee);
            employeeVector.add(employee);
            employeeIndex=employeeList.indexOf(employee);
        }
    }
    @Benchmark
    public void testAddAt(ArrayListBenchmark.MyState state){
        state.employeeList.add((int) (state.iterations), new Employee(state.iterations, "John"));
    }
    @Benchmark
    public boolean testContains(ArrayListBenchmark.MyState state){
        return state.employeeList.contains(state.employee);
    }
    @Benchmark
    public boolean testContainsVector(ArrayListBenchmark.MyState state){
        return state.employeeVector.contains(state.employee);
    }
    @Benchmark
    public int testIndexOf(ArrayListBenchmark.MyState state){
        return state.employeeList.indexOf(state.employee);
    }
    @Benchmark
    public Employee testGet(ArrayListBenchmark.MyState state){
        return state.employeeList.get(state.employeeIndex);
    }
    @Benchmark
    public Employee testVectorGet(ArrayListBenchmark.MyState state){
        return state.employeeVector.get(state.employeeIndex);
    }
    @Benchmark
    public boolean testRemove(ArrayListBenchmark.MyState state){
        return state.employeeList.remove(state.employee);
    }
    @Benchmark
    public void testAdd(ArrayListBenchmark.MyState state){
        state.employeeList.add(new Employee(state.iterations +1,"John"));
    }

    public static void main(String[] args) throws RunnerException {
        Options options=new OptionsBuilder()
                .include(ArrayListBenchmark.class.getSimpleName()).threads(3)
                .forks(1).shouldFailOnError(true)
                .shouldDoGC(true)
                .jvmArgs("-server").build();
        new Runner(options).run();
    }


}
