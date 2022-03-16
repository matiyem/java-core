package com.example.synchronizationBadPractices;

/*
    Create by Atiye Mousavi 
    Date: 3/15/2022
    Time: 10:58 AM
**/
public class SynchronizationSolutionExample {
    private final String stringLock=new String("LOCK_STRING");
    public void stringSolution(){
        synchronized (stringLock){
            //...
        }
    }
    private int count=0;
    private final Integer intLock=new Integer(count);
    public void boxedPrimitiveSolution(){
        synchronized (intLock){
            count++;
            //...
        }
    }
    private static int staticCount=0;
    private static final Object staticObjLock=new Object();
    public void staticVariableSolution(){
        synchronized (staticObjLock){
            staticCount++;
        }
    }
}
