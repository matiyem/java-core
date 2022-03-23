package com.example.globalExceptionHandler;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 5:16 PM
*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParseExceptionExample {
    private static Logger LOGGER = LoggerFactory.getLogger(ParseExceptionExample.class);

    public static void main(String[] args) {

        DateFormat format = new SimpleDateFormat("MM, dd, yyyy");

        try {
            format.parse("01, , 2010");
        } catch (ParseException e) {
            LOGGER.error("ParseException caught!");
        }
    }
}
