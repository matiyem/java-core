package com.example.chainedException.exception;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 10:09 AM
*/


public class TeamLeadUpsetException extends Exception {
    public TeamLeadUpsetException(String message,Throwable cause){
        super(message,cause);
    }
    public TeamLeadUpsetException(String message){
        super(message);
    }
}
