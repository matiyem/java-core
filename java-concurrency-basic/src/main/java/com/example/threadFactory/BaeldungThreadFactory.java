package com.example.threadFactory;

import java.util.concurrent.ThreadFactory;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 11:32 AM
**/
public class BaeldungThreadFactory implements ThreadFactory {

    private int threadId;
    private String name;

    public BaeldungThreadFactory(String name) {
        threadId = 1;
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "-Thread_" + threadId);
        System.out.println("create new thread with id : " + threadId + " and name : " + t.getName());
        threadId++;
        return t;
    }
}
