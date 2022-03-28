package com.example.nullMethodParameter;

/*
    created by Atiye Mousavi
    Date: 3/24/2022
    Time: 5:50 PM
*/


public class NullParameterExample {
    public void processSomethingNotNull(Object myParameter) {
        if (myParameter == null) {
            throw new IllegalArgumentException("Parameter 'myParameter' cannot be null");
        }
    }

    public void processSomethingElseNotNull(Object myParameter) {
        if (myParameter == null) {
            throw new NullPointerException("Parameter 'myParameter' cannot be null");
        }
    }
}
