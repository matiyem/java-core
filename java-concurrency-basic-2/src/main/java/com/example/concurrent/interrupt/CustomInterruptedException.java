package com.example.concurrent.interrupt;

/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 12:00 PM
**/
public class CustomInterruptedException extends Exception {
    private static final long serialVersionUID = 1L;
    CustomInterruptedException(String message){
        super(message);
    }
}
