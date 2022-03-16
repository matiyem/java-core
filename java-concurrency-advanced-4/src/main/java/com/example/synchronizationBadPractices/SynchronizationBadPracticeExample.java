package com.example.synchronizationBadPractices;

/*
    Create by Atiye Mousavi 
    Date: 3/15/2022
    Time: 10:33 AM
**/
public class SynchronizationBadPracticeExample {

    public void stringBadPractice1() {
        String stringLock = "LOCK_STRING";
        synchronized (stringLock) {

        }
    }

    private final String stringLock = "LOCK_STRING";

    public void stringBadPractice2() {
        synchronized (stringLock) {
            //...
        }
    }

    private final String internedStringLock = new String("lOCK_STRING").intern();

    public void stringBadPractice3() {
        synchronized (internedStringLock){

        }
    }
    private final Boolean booleanLock=Boolean.FALSE;
    public void booleanBadPractice(){
        synchronized (booleanLock){

        }
    }
    private int count=0;
    private final Integer intLock=count;
    public void boxedPrimitiveBadPractice(){
        synchronized (intLock){
            count++;
            //...
        }
    }
    public void classBadPractice() throws InterruptedException {
        AnimalBadPractice animalObj=new AnimalBadPractice("Tommy","John");
        synchronized (animalObj){
            while (true){
                Thread.sleep(Integer.MAX_VALUE);
            }
        }
    }
}
