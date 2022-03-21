package com.example.customException;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 12:04 PM
*/


public class IncorrectFileNameException extends Exception {
    private static final long serialVersionUID = 1L;

    public IncorrectFileNameException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
