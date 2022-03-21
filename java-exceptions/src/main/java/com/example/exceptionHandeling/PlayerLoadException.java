package com.example.exceptionHandeling;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 12:18 PM
*/


import java.io.IOException;

public class PlayerLoadException extends Exception {
    public PlayerLoadException(IOException io) {
        super(io);
    }
}
