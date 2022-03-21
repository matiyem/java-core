package com.example.chainedException.exception;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 10:06 AM
*/


public class NoLeaveGrantedException extends Exception{

    public NoLeaveGrantedException(String message,Throwable cause){
        super(message,cause);
    }
    public NoLeaveGrantedException(String message){
        super(message);
    }
}
