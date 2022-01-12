package com.example.removal;

import java.util.ArrayList;
import java.util.Collection;

/*
    Create by Atiye Mousavi 
    Date: 1/10/2022
    Time: 4:03 PM
**/
public class CollectionRemoveIf {

    public static void main(String[] args) {
        Collection<String> names = new ArrayList<>();
        names.add("John");
        names.add("Ana");
        names.add("Mary");
        names.add("Anthony");
        names.add("Mark");

        names.removeIf(e -> e.startsWith("A"));
        System.out.print(String.join(",", names));
    }
}
