package com.example.removal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/*
    Create by Atiye Mousavi 
    Date: 1/11/2022
    Time: 9:20 AM
**/
public class StreamFilterAndCollector {
    public static void main(String[] args) {
        Collection<String> names=new ArrayList<>();
        names.add("John");
        names.add("Ana");
        names.add("Mary");
        names.add("Anthony");
        names.add("Mark");

        Collection<String> filterCollection=names
                .stream()
                .filter(e-> !e.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println(String.join(",",filterCollection));
    }
}
