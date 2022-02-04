package com.example.concurrent.atomic;

/*
    Create by Atiye Mousavi 
    Date: 1/30/2022
    Time: 3:41 PM
**/
public class UnsafeCounter {
    private int counter;

    int getValue(){
        return counter;
    }
    void increment(){
        counter++;
    }
}
