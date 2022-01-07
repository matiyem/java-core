package com.example.interfaceVsAbstractClass;

import java.util.Arrays;
import java.util.List;

/**
 * Create by Atiye Mousavi
 * Date: 1/5/2022
 * Time: 1:36 PM
 **/
public abstract class CircleClass {
    private String color;
    private List<String> allowedColor= Arrays.asList("RED","GREEN","bLUE");

    public boolean isValid(){
        return allowedColor.contains(getColor());
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color=color;
    }
}
