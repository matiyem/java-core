package com.example.threadSafety.service;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 1:02 PM
**/
public class StateHolder {

    private final String state;

    public StateHolder(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
