package com.example;

/*
    Create by Atiye Mousavi 
    Date: 1/29/2022
    Time: 4:35 PM
**/
public class MyCounter {
    private int count;

    public void increment() {
        int temp = count;
        count = temp + 1;
    }
    public synchronized void incrementWithWait() throws InterruptedException {
        int temp=count;
        wait(100);
        count=temp+1;
    }

    public int getCount() {
        return count;
    }
}
