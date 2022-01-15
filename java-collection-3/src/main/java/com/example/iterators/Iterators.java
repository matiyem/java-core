package com.example.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/*
    Create by Atiye Mousavi 
    Date: 1/12/2022
    Time: 3:24 PM
**/
public class Iterators {

    public static int failFast1() {
        ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);

        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            numbers.add(50);
        }
        return numbers.size();

    }
    public static int failFast2() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);

        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 30) {
                // will not throw Exception
                iterator.remove();
            }
        }

        System.out.println("using iterator's remove method = " + numbers);

        iterator = numbers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 40) {
                // will throw Exception on
                // next call of next() method
                numbers.remove(2);
            }
        }

        return numbers.size();
    }

    public static int failSafe1() {
//        در قطعه کد بالا، ما از Fail-Safe Iterator استفاده می کنیم. بنابراین، حتی اگر یک عنصر جدید در طول تکرار به مجموعه اضافه شود، استثنایی ایجاد نمی کند.
//
//تکرار کننده پیش فرض برای ConcurrentHashMap سازگاری ضعیفی دارد. این بدان معناست که این Iterator می‌تواند تغییرات همزمان را تحمل کند، عناصر را همانطور که در زمان ساخت Iterator وجود داشته‌اند عبور می‌دهد و ممکن است (اما تضمین نشده است) تغییراتی را در مجموعه پس از ساخت Iterator منعکس کند.
//
//از این رو، در قطعه کد بالا، تکرار پنج بار حلقه می شود، به این معنی که عنصر جدید اضافه شده به مجموعه را شناسایی می کند.
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        map.put("First", 10);
        map.put("Second", 20);
        map.put("Third", 30);
        map.put("Fourth", 40);

        Iterator<String> iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            map.put("Fifth", 50);
        }
        return map.size();
    }
}
