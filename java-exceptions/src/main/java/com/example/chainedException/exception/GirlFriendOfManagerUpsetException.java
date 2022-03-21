package com.example.chainedException.exception;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 10:01 AM
*/


public class GirlFriendOfManagerUpsetException extends Exception{

    public GirlFriendOfManagerUpsetException(String message,Throwable cause){
        super(message, cause);
    }
    public GirlFriendOfManagerUpsetException(String message){
        super(message);
    }
}
