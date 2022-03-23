package com.example.tryWithResource;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 7:44 PM
*/


public class MyResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("Closed MyResource");

    }
}
