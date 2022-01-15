package com.example.sortingComparison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
    Create by Atiye Mousavi 
    Date: 1/12/2022
    Time: 3:47 PM
**/
public class CollectionsSortCompare {
    public static void main(String[] args) {

    }

    private static void sortReferenceType() {
        Integer[] numbers = {5, 22, 10, 0};
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }

    private static void sortCollection() {
        List<Integer> numbersList = new ArrayList<>();
        numbersList.add(5);
        numbersList.add(22);
        numbersList.add(10);
        numbersList.add(0);

        Collections.sort(numbersList);

        numbersList.forEach(System.out::println);
    }

    private static void sortPrimitives() {
        int[] numbers = {5, 22, 10, 0};
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers));
    }
}
