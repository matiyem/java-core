package com.example.exception.indexOutOfBounds;

/*
    created by Atiye Mousavi
    Date: 3/23/2022
    Time: 5:03 PM
*/


import java.util.ArrayList;
import java.util.List;

public class CopyListUsingAddAllMethodDemo {
    static List<Integer> copyList(List<Integer> source){
        List<Integer> destination=new ArrayList<>();
        destination.addAll(source);
        return destination;
    }
}
