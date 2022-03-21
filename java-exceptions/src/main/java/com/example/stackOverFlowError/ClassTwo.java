package com.example.stackOverFlowError;

/*
    Create by Atiye Mousavi 
    Date: 3/19/2022
    Time: 10:17 AM
**/
public class ClassTwo {
    private int twoValue;
    private ClassOne clsOneInstance=null;

    public ClassTwo() {
        twoValue=10;
        clsOneInstance=new ClassOne();
    }
    public ClassTwo(int twoValue,ClassOne clsOneInstance){
        this.twoValue=twoValue;
        this.clsOneInstance=clsOneInstance;
    }
}
