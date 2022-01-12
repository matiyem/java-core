package com.example.filtering;

import org.eclipse.collections.impl.factory.Lists;

import java.util.Collection;

import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.impl.utility.Iterate;

/*
    Create by Atiye Mousavi 
    Date: 1/10/2022
    Time: 2:15 PM
**/
public class EclipseCollectionsCollectionFilter {
    //اگر برنامه از جاوا 8 پشتیبانی نمیکرد میتوانیم از این کتابخانه استفاده برای فیلتر کردن
    static public Collection<Integer> findEvenNumbers(Collection<Integer> baseCollection) {
        Predicate<Integer> eclipsePredicate = item -> item % 2 == 0;
        Collection<Integer> filteredList = Lists.mutable.ofAll(baseCollection).select(eclipsePredicate);
        return filteredList;
    }

    static public Collection<Integer> findEvenNumbersUsingIterate(Collection<Integer> baseCollection) {
        Predicate<Integer> eclipsePredicate = new Predicate<Integer>() {
            @Override
            public boolean accept(Integer integer) {
                return integer % 2 == 0;
            }
        };
        Collection<Integer> filteredList = Iterate.select(baseCollection, eclipsePredicate);
        return filteredList;
    }
}
