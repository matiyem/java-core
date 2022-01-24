package com.example.threadSafety.mathutils;

import java.math.BigInteger;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 11:42 AM
**/
public class MathUtils {
    public static BigInteger factorial(int number) {
        BigInteger f = new BigInteger("1");
        for (int i = 2; i <= number; i++) {
            f = f.multiply(BigInteger.valueOf(i));
        }
        return f;
    }
}
