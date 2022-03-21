package com.example.exceptionHandeling;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 12:47 PM
*/


public class TimeoutException extends Exception {
    public TimeoutException(String message) {
        super(message);
    }
}
