package com.example.mulipletypesinmap;

import java.util.Arrays;

/*
    Create by Atiye Mousavi 
    Date: 1/16/2022
    Time: 9:54 AM
**/
public class IntArrayTypeValue implements DynamicTypeValue{
    private int[] value;

    public IntArrayTypeValue(int[] value){
        this.value=value;
    }
    @Override
    public String valueDescription() {
        if (value==null){
            return "The value is null.";
        }
        return String.format("The value is an array of %d integers: %s",value.length, Arrays.toString(value));
    }
}
