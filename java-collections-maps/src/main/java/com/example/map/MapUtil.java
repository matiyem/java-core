package com.example.map;

/*
    created by Atiye Mousavi
    Date: 4/1/2022
    Time: 12:50 PM
*/


import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class MapUtil {

    public static <K, V> Stream<K> keys(Map<K,V> map,V value){
        return map.entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey);
    }
    public static <K,V> Set<K> getKeys(Map<K,V> map,V value){
        Set<K> keys=new HashSet<>();
        for (Map.Entry<K,V> entry : map.entrySet()){
            if (entry.getValue().equals(value)){
                keys.add(entry.getKey());
            }
        }
        return keys;
    }
    public static <K,V> K getKey(Map<K,V> map,V value){
        for (Map.Entry<K,V> entry: map.entrySet()){
            if (entry.getValue().equals(value)){
                return entry.getKey();
            }
        }
        return null;

    }
}
