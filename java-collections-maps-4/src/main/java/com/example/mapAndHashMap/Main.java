package com.example.mapAndHashMap;

import com.example.mapAndHashMap.printer.HashMapPrinter;
import com.example.mapAndHashMap.printer.MapPrinter;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
    Create by Atiye Mousavi 
    Date: 4/9/2022
    Time: 12:07 PM
**/
public class Main {
    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();
        HashMap<String,String> hashMap=new HashMap<>();
        TreeMap<String,String> treeMap=new TreeMap<>();

        HashMapPrinter hashMapPrinter=new HashMapPrinter();
        hashMapPrinter.printMap(hashMap);

        MapPrinter mapPrinter=new MapPrinter();
        mapPrinter.printMap(hashMap);
        mapPrinter.printMap(treeMap);
        mapPrinter.printMap(map);
    }
}
