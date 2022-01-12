package com.example.filtering;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

import org.apache.commons.collections4.Predicate;

/*
    Create by Atiye Mousavi 
    Date: 1/10/2022
    Time: 12:58 PM
**/
public class CollectionUtilsCollectionFilter {
    //اگر برنامه از جاوا 8 پشتیبانی نمیکرد میتوانیم از این کتابخانه استفاده برای فیلتر کردن
    static public Collection<Integer> findEvenNumbers(Collection<Integer> baseCollection) {
        Predicate<Integer> apacheEvenNumberPredicate = item -> item % 2 == 0;
        CollectionUtils.filter(baseCollection, apacheEvenNumberPredicate);
        return baseCollection;
    }
}
