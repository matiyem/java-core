package com.example.synchronizationBadPractices;

/*
    Create by Atiye Mousavi 
    Date: 3/15/2022
    Time: 10:30 AM
**/
public class AnimalSolution {
    private final Object objLock1 = new Object();
    private final Object objLock2 = new Object();

    private String name;
    private String owner;

    public Object getObjLock1() {
        return objLock1;
    }

    public Object getObjLock2() {
        return objLock2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        synchronized (objLock1) {
            this.name = name;
        }
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        synchronized (this) {
            this.owner = owner;
        }
    }

    public AnimalSolution() {
    }

    public AnimalSolution(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }
}
