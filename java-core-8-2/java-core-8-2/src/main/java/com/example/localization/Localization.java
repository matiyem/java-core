package com.example.localization;


import java.util.List;
import java.util.Locale;

import java.util.ResourceBundle;

/**
 * Create by Atiye Mousavi
 * Date: 1/5/2022
 * Time: 2:06 PM
 **/
public class Localization {
    public static String getLable(Locale local){
        final ResourceBundle bundle=ResourceBundle.getBundle("message",local);
        return bundle.getString("lable");
    }
    public static void run(List<Locale> locales){
        locales.forEach(locale -> System.out.println(getLable(locale)));
    }

}
