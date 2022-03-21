package com.example.throwVsThrows;

/*
    Create by Atiye Mousavi 
    Date: 3/19/2022
    Time: 10:31 AM
**/
public class DataAccessException extends RuntimeException {
    public DataAccessException(String message,Throwable cause){
        super(message, cause);
    }
}
