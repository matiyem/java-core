package com.example.localization;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Create by Atiye Mousavi
 * Date: 1/5/2022
 * Time: 1:42 PM
 **/
public class App {
    public static void main(String[] args) {
        List<Locale> locales= Arrays.asList(new Locale[]{Locale.UK,Locale.ITALY,Locale.FRENCH,Locale.forLanguageTag("pl-PL")});
        Localization.run(locales);
        JavaSEFormat.run(locales);

    }
}
