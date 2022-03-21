package com.example.stackOverFlowError;

/*
    Create by Atiye Mousavi 
    Date: 3/19/2022
    Time: 10:21 AM
**/
public class InfiniteRecursionWithTerminationCondition {
    public int calculateFactorial(final int number) {
        return number == 1 ? 1 : number * calculateFactorial(number - 1);
    }
}
