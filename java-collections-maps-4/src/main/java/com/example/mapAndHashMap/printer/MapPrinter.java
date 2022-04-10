package com.example.mapAndHashMap.printer;

import java.util.Map;

/*
    Create by Atiye Mousavi 
    Date: 4/9/2022
    Time: 12:00 PM
**/
public class MapPrinter {
    public void printMap(final Map<?,?> map){
        for (final Map.Entry<?,?> entry: map.entrySet()){
            System.out.println(entry.getKey()+" " + entry.getValue());
        }
    }
}
