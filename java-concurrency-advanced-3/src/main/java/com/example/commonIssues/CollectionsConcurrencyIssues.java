package com.example.commonIssues;

/*
    created by Atiye Mousavi
    Date: 2/15/2022
    Time: 3:02 PM
*/


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionsConcurrencyIssues {

    private void putIfAbsentList_NonAtomicOperation_ProneToConcurrencyIssues() {
//        هر عملیات لیست ما همگام‌سازی می‌شود، اما هر ترکیبی از فراخوانی‌های چند روش همگام‌سازی نمی‌شود. به طور خاص، بین دو عملیات، یک رشته دیگر می تواند مجموعه ما را تغییر دهد و منجر به نتایج نامطلوب شود.
//
//به عنوان مثال، دو رشته می توانند همزمان وارد بلوک if شوند و سپس لیست را به روز کنند و هر رشته مقدار foo را به لیست اضافه کند.
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        if (!list.contains("foo")) {
            list.add("foo");
        }
    }

    private void putIfAbsentList_AtomicOperation_ThreadSafe() {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        //راه حل کد بالا کد زیر است
        synchronized (list) {
            if (!list.contains("foo")) {
                list.add("foo");
            }
        }
    }

    private void putIfAbsentMap_AtomicOperation_BetterApproach() {
        Map<String, String> map = new ConcurrentHashMap<>();
        synchronized (map) {
            if (!map.containsKey("foo")) {
                map.put("foo", "bar");
            }
        }
    }

    private void putIfAbsentMap_AtomicOperation_BestApproach() {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
    }

    private void computeIfAbsentMap_AtomicOperation() {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.computeIfAbsent("foo", key -> key + "bar");
    }
}
