package com.example.stackOverFlowError;

/*
    Create by Atiye Mousavi 
    Date: 3/19/2022
    Time: 10:16 AM
**/
public class ClassOne {
    private int oneValue;
    private ClassTwo clsTwoInstance=null;

    public ClassOne(){
        oneValue=0;
        clsTwoInstance=new ClassTwo();
    }
    public ClassOne(int oneValue,ClassTwo clsTwoInstance){
        this.oneValue=oneValue;
        this.clsTwoInstance=clsTwoInstance;
    }
}
