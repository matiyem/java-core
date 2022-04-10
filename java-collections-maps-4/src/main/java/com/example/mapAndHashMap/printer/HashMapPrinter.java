package com.example.mapAndHashMap.printer;

import java.util.HashMap;
import java.util.Map;

/*
    Create by Atiye Mousavi 
    Date: 4/9/2022
    Time: 11:58 AM
**/
public class HashMapPrinter {
    public void printMap(final HashMap<?, ?> map) {
        for (final Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
