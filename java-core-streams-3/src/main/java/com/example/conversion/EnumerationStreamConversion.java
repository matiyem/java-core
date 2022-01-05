package com.example.conversion;

import java.util.Enumeration;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Create by Atiye Mousavi
 * Date: 1/3/2022
 * Time: 2:54 PM
 **/
public class EnumerationStreamConversion {
    public static <T>Stream<T> convert(Enumeration<T> enumeration){
        //برای تبدیل enumeration به استریم است
        EnumerationSpliterator<T> spliterator=new EnumerationSpliterator<T>(Long.MAX_VALUE, Spliterator.ORDERED,enumeration);
        Stream<T> stream= StreamSupport.stream(spliterator,false);
        return stream;
    }
}
