package com.example.mapAndHashMap.printer;

import java.util.Map;

/*
    Create by Atiye Mousavi 
    Date: 4/9/2022
    Time: 12:03 PM
**/
public class MapReporter {
    private final Map<?, ?> map;

    public MapReporter(Map<?, ?> map) {
        this.map = map;
    }

    public void printMap() {
        for (final Map.Entry<?, ?> entry : this.map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
