package com.example.filtering;

import com.google.common.collect.Collections2;

import com.google.common.base.Predicate;

import java.util.Collection;

/*
    Create by Atiye Mousavi 
    Date: 1/10/2022
    Time: 3:23 PM
**/
public class GuavaCollectionFilter {
    //اگر برنامه از جاوا 8 پشتیبانی نمیکرد میتوانیم از این کتابخانه استفاده برای فیلتر کردن

    static public Collection<Integer> findEvenNumbers(Collection<Integer> baseCollection){
        Predicate<Integer> guavaPredicate=item -> item %2==0;

        Collection<Integer> filteredCollection= Collections2.filter(baseCollection,guavaPredicate);
        return filteredCollection;
    }
}
