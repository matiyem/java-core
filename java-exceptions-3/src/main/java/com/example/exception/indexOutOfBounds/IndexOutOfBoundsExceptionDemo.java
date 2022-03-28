package com.example.exception.indexOutOfBounds;

/*
    created by Atiye Mousavi
    Date: 3/23/2022
    Time: 6:07 PM
*/


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IndexOutOfBoundsExceptionDemo {
    static List<Integer> copyList(List<Integer> source){
        List<Integer> destination=new ArrayList<>(source.size());
        Collections.copy(destination,source);
        return destination;
    }
}
