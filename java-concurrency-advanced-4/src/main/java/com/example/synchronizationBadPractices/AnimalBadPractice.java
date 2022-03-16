package com.example.synchronizationBadPractices;

/*
    Create by Atiye Mousavi 
    Date: 3/15/2022
    Time: 10:24 AM
**/
public class AnimalBadPractice {
    private String name;
    private String owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        synchronized (this) {
            this.owner = owner;
        }
    }

    public AnimalBadPractice() {
    }

    public AnimalBadPractice(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }
}
