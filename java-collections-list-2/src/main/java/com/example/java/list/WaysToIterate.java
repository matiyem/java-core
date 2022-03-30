package com.example.java.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*
    Create by Atiye Mousavi 
    Date: 3/29/2022
    Time: 1:52 PM
**/
public class WaysToIterate {
    List<String> countries = Arrays.asList("Germany", "Panama", "Australia");

    public void iterateWithForLoop() {
        for (int i = 0; i < countries.size(); i++) {
            System.out.println(countries.get(i));
        }
    }

    public void iterateWithEnhancedForLoop() {
        for (String country : countries) {
            System.out.println(country);
        }
    }

    public void iterateWithIterator() {
        Iterator<String> countriesIterator = countries.iterator();
        while (countriesIterator.hasNext()) {
            System.out.println(countriesIterator.next());
        }
    }

    public void iterateWithListIterator() {
        ListIterator<String> listIterator = countries.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
    }
    public void iterateWithForEach(){
        countries.forEach(System.out::println);
    }
    public void iterateWithStreamForEach(){
        countries.stream().forEach((c)-> System.out.println(c));
    }


}
