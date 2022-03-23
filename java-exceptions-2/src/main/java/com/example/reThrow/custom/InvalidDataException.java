package com.example.reThrow.custom;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 5:24 PM
*/


public class InvalidDataException extends Exception {
    public InvalidDataException(Exception e) {
        super(e);
    }

}
