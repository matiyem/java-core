package com.example.exceptions.localization;

/*
    created by Atiye Mousavi
    Date: 3/24/2022
    Time: 12:02 PM
*/


import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
    public static String getMessageForLocale(String messageKey, Locale locale){
        return ResourceBundle.getBundle("message",locale).getString(messageKey);
    }
}
