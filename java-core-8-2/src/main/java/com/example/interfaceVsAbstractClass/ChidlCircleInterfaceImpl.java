package com.example.interfaceVsAbstractClass;

/**
 * Create by Atiye Mousavi
 * Date: 1/5/2022
 * Time: 1:39 PM
 **/
public class ChidlCircleInterfaceImpl implements CircleInterface{
    private String color;
    @Override
    public String getColor() {
        return color;
    }
    public void setColor(String color){
        this.color=color;
    }
}
