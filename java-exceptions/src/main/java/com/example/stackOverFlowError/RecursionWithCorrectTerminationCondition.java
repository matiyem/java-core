package com.example.stackOverFlowError;

/*
    Create by Atiye Mousavi 
    Date: 3/19/2022
    Time: 10:24 AM
**/
public class RecursionWithCorrectTerminationCondition {
    public int calculateFactorial(final int number){
        return number<=1 ? 1: number * calculateFactorial(number -1);
    }
}
