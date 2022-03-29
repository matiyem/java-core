package com.example.java.list;

/*
    created by Atiye Mousavi
    Date: 3/28/2022
    Time: 5:13 PM
*/


import com.google.common.collect.Lists;
import org.apache.commons.collections4.iterators.ReverseListIterator;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class ReverseIterator {
    public void iterateUsingForLoop(final List<String> list){
        for (int i = list.size(); i-- > 0; ) {
            System.out.println(list.get(i));
        }
    }
    public void iterateUsingListIterator(final List<String> list) {

        final ListIterator listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
    }
    public void iterateUsingCollections(final List<String> list) {

        Collections.reverse(list);
        for (final String item : list) {
            System.out.println(item);
        }
    }
    public void iterateUsingApacheReverseListIterator(final List<String> list) {

        final ReverseListIterator listIterator = new ReverseListIterator(list);
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
    }
    public void iterateUsingGuava(final List<String> list) {

        final List<String> reversedList = Lists.reverse(list);
        for (final String item : reversedList) {
            System.out.println(item);
        }
    }
}
