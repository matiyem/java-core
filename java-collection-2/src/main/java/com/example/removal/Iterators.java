package com.example.removal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
    Create by Atiye Mousavi 
    Date: 1/10/2022
    Time: 4:13 PM
**/
public class Iterators {
    public static void main(String[] args) {
        Collection<String> names=new ArrayList<>();
        names.add("John");
        names.add("Ana");
        names.add("Mary");
        names.add("Anthony");
        names.add("Mark");

        Iterator<String> i=names.iterator();

        while(i.hasNext()){
            String e= i.next();
            if (e.startsWith("A")){
                i.remove();
            }
        }
        System.out.println(String.join(",",names));

    }
}
