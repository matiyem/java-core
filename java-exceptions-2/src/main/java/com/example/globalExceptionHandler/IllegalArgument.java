package com.example.globalExceptionHandler;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 2:44 PM
*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IllegalArgument {
    private static Logger LOGGER = LoggerFactory.getLogger(IllegalArgument.class);

    public static void main(String[] args) {
        try {
            Thread.sleep(-1000);
        } catch (InterruptedException e) {
            LOGGER.error("IllegalArgumentException caught!");
        }
        ;
    }
}
