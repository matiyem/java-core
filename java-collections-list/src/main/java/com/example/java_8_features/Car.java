package com.example.java_8_features;

/*
    created by Atiye Mousavi
    Date: 3/28/2022
    Time: 6:08 PM
*/


public class Car {
    private String model;
    private int topSpeed;

    public Car(String model, int topSpeed) {
        this.model = model;
        this.topSpeed = topSpeed;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }
}
