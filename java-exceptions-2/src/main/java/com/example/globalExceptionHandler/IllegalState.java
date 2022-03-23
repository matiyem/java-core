package com.example.globalExceptionHandler;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 2:45 PM
*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IllegalState {
    private static Logger LOGGER = LoggerFactory.getLogger(IllegalState.class);

    public static void main(String[] args) {
        List<Integer> intList=new ArrayList<>();

        for (int i=0 ; i<10 ; i++){
            intList.add(i);
        }
        Iterator<Integer> initListIterator= intList.iterator();

        try {
            initListIterator.remove();
        } catch (Exception e) {
            LOGGER.error("IllegalStateException caught!");
        }
    }
}
