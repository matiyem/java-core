package com.example.concurrent.daemon;

/*
    Create by Atiye Mousavi 
    Date: 1/31/2022
    Time: 10:05 AM
**/
public class SingleThreadExample {
    public static void main(String[] args) {
        NewThread t=new NewThread();
        t.start();
    }
}
