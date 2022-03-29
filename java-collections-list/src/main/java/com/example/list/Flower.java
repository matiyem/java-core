package com.example.list;

/*
    created by Atiye Mousavi
    Date: 3/28/2022
    Time: 7:31 PM
*/


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
