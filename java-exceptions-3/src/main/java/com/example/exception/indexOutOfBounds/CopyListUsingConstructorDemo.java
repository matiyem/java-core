package com.example.exception.indexOutOfBounds;

/*
    created by Atiye Mousavi
    Date: 3/23/2022
    Time: 5:42 PM
*/


import java.util.ArrayList;
import java.util.List;

public class CopyListUsingConstructorDemo {
    static List<Integer> copyList(List<Integer> source){
        return new ArrayList<>(source);
    }
}
