package com.example.globalExceptionHandler;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 12:40 PM
*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayIndexOutOfBounds {
    private static Logger LOGGER = LoggerFactory.getLogger(ArrayIndexOutOfBounds.class);

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        try {
            int numFromNegativeIndex = nums[-1];
            int numFromGreaterIndex = nums[4];
            int numFromLengthIndex = nums[3];
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.error("ArrayIndexOutOfBoundsException");
        }
    }


}
