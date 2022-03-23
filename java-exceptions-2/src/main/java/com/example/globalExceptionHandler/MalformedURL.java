package com.example.globalExceptionHandler;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 4:04 PM
*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class MalformedURL {
    private static Logger LOGGER= LoggerFactory.getLogger(MalformedURL.class);

    public static void main(String[] args) {
        URL bealdungURL=null;

        try {
            bealdungURL=new URL("malformedurl");
        } catch (MalformedURLException e) {
            LOGGER.error("MalformedURLException caught!");
        }
    }
}
