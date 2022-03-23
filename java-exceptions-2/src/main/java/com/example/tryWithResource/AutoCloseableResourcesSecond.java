package com.example.tryWithResource;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 7:41 PM
*/


public class AutoCloseableResourcesSecond implements AutoCloseable{
    public AutoCloseableResourcesSecond() {
        System.out.println("Constructor -> AutoCloseableResources_Second");
    }

    public void doSomething() {
        System.out.println("Something -> AutoCloseableResources_Second");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closed AutoCloseableResources_Second");
    }
}
