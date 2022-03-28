package com.example.exception.indexOutOfBounds;

/*
    created by Atiye Mousavi
    Date: 3/23/2022
    Time: 5:38 PM
*/


import java.util.Collections;
import java.util.List;

public class CopyListUsingCollectionsCopyMethodDemo {
    static void copyList(List<Integer> source,List<Integer> destination){
        Collections.copy(destination,source);
    }
}
