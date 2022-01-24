package com.example.callable;
import java.util.concurrent.Callable;

/*
    Create by Atiye Mousavi 
    Date: 1/17/2022
    Time: 10:59 AM
**/
public class FactorialTask implements Callable<Integer> {
    int number;

    public FactorialTask(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws InvalidParamaterException {
        int fact = 1;
        if (number < 0)
            throw new InvalidParamaterException("Number must be positive");
        for (int count = number; count > 1; count--) {
            fact = fact * count;
        }
        return fact;
    }
    private class InvalidParamaterException extends Exception{
        public InvalidParamaterException(String message) {
            super(message);
        }
    }
}
