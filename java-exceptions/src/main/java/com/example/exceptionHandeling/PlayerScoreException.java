package com.example.exceptionHandeling;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 12:46 PM
*/


public class PlayerScoreException extends Exception {
    public PlayerScoreException(Exception e){
        super(e);
    }
}
