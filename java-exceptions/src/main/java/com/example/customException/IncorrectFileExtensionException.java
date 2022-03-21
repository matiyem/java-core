package com.example.customException;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 11:32 AM
*/


public class IncorrectFileExtensionException extends RuntimeException{
    private static final long serialVersionUID=1L;

    public IncorrectFileExtensionException(String errorMessage,Throwable err){
        super(errorMessage,err);
    }
}
