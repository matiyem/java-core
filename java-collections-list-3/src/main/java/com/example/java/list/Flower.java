package com.example.java.list;

/*
    Create by Atiye Mousavi 
    Date: 3/30/2022
    Time: 1:01 PM
**/
public class Flower {
    private String name;
    private int petals;

    public Flower(String name, int petals) {
        this.name = name;
        this.petals = petals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPetals() {
        return petals;
    }

    public void setPetals(int petals) {
        this.petals = petals;
    }
}
