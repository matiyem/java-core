package com.example.chainedException.exception;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 10:05 AM
*/


public class ManagerUpsetException extends Exception{

    public ManagerUpsetException(String message,Throwable cause){
        super(message, cause);
    }
    public ManagerUpsetException(String message){
        super(message);
    }
}
