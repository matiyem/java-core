package com.example.globalExceptionHandler;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 12:26 PM
*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Arithmetic {
    private static Logger LOGGER = LoggerFactory.getLogger(Arithmetic.class);

    public static void main(String[] args) {
        try {
            int result = 30 / 0;
        } catch (ArithmeticException e) {
            LOGGER.error("ArithmeticException caught!");
        }
    }
}
