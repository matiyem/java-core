package com.example.mutex;

/*
    Create by Atiye Mousavi 
    Date: 1/29/2022
    Time: 3:55 PM
**/
public class SequenceGenerator {
    private int currentValue = 0;

    public int getNextSequence() {
        currentValue = currentValue + 1;
        return currentValue;
    }
}
