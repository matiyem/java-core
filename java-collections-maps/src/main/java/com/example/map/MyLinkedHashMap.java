package com.example.map;

/*
    created by Atiye Mousavi
    Date: 4/1/2022
    Time: 12:37 PM
*/


import java.util.LinkedHashMap;
import java.util.Map;

public class MyLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    private static final long serialVersionUID=1L;
    private static final int MAX_ENTRIES=5;

    public MyLinkedHashMap(int initialCapacity,float loadFactor,boolean accessOrder){
      super(initialCapacity,loadFactor,accessOrder);
    }
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }
}
