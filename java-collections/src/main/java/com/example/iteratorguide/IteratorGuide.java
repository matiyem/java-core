package com.example.iteratorguide;

import jdk.nashorn.internal.runtime.arrays.IteratorAction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Create by Atiye Mousavi
 * Date: 1/8/2022
 * Time: 11:04 AM
 **/
public class IteratorGuide {
    public static void main(String[] args) {
        List<String> items=new ArrayList<>();
        items.add("ONE");
        items.add("TWO");
        items.add("THREE");
        Iterator<String> iter=items.iterator();
        while(iter.hasNext()){
            String next=iter.next();
            System.out.println(next);
            iter.remove();
        }
        ListIterator<String> listIterator=items.listIterator();
        while(listIterator.hasNext()){
            String nextWithIndex= items.get(listIterator.nextIndex());
            String next= listIterator.next();
            if ("ONE".equals(next)){
                listIterator.set("SWAPPED");
            }
        }
        listIterator.add("Four");
        while(listIterator.hasPrevious()){
            String previousWithIndex=items.get(listIterator.previousIndex());
            String pervious=listIterator.previous();
            System.out.println(pervious);
        }
//        از جاوا 8، ما متد forEachRemaining را داریم که امکان استفاده از لامبدا را برای پردازش عناصر باقی مانده فراهم می کند:
        listIterator.forEachRemaining(e->{
            System.out.println(e);
        });
    }
}
