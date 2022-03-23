package com.example.globalExceptionHandler;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 5:21 PM
*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringIndexOutOfBounds {
    private static Logger LOGGER = LoggerFactory.getLogger(StringIndexOutOfBounds.class);

    public static void main(String[] args) {

        String str = "Hello World";

        try {
            char charAtNegativeIndex = str.charAt(-1); // Trying to access at negative index
            char charAtLengthIndex = str.charAt(11); // Trying to access at index equal to size of the string
        } catch (StringIndexOutOfBoundsException e) {
            LOGGER.error("StringIndexOutOfBoundsException caught");
        }

    }
}
