package com.example.exception.indexOutOfBounds;

/*
    created by Atiye Mousavi
    Date: 3/23/2022
    Time: 6:06 PM
*/


import java.util.List;
import java.util.stream.Collectors;

public class CopyListUsingJava8StreamDemo {
    static List<Integer> copyList(List<Integer> source){
        return source.stream().collect(Collectors.toList());
    }
}
