package com.example.stackOverFlowError;

/*
    Create by Atiye Mousavi 
    Date: 3/19/2022
    Time: 10:26 AM
**/
public class UnintendedInfiniteRecursion {
    public int calculateFactorial(int number){
        return number * calculateFactorial(number-1);
    }
}
