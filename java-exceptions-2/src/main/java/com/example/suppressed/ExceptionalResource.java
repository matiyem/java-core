package com.example.suppressed;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 7:10 PM
*/


public class ExceptionalResource implements AutoCloseable {
    public void processSomething() {
        throw new IllegalArgumentException("Thrown from processSomething()");
    }

    @Override
    public void close() throws Exception {
        throw new NullPointerException("Thrown from close()");

    }
}
