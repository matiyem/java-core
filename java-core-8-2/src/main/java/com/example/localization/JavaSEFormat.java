package com.example.localization;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Create by Atiye Mousavi
 * Date: 1/5/2022
 * Time: 3:10 PM
 **/
public class JavaSEFormat {
    public static String getLable(Locale locale,Object[] data){
        ResourceBundle bundle=ResourceBundle.getBundle("formats",locale);
        final String pattern=bundle.getString("label");
        final MessageFormat formater=new MessageFormat(pattern,locale);
        return formater.format(data);
    }
    public static void run(List<Locale> locales){
        System.out.println("java formatter");
        final Date data=new Date(System.currentTimeMillis());
        locales.forEach(locale -> System.out.println(getLable(locale,new Object[]{data,"Alice",0})));
        locales.forEach(locale -> System.out.println(getLable(locale,new Object[]{data,"Alice",2})));
    }
}
