package com.example.tryWithResource;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 7:39 PM
*/


public class AutoCloseableResourcesFirst implements AutoCloseable{
    public AutoCloseableResourcesFirst() {
        System.out.println("Constructor -> AutoCloseableResources_First");
    }

    public void doSomething() {
        System.out.println("Something -> AutoCloseableResources_First");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closed AutoCloseableResources_First");
    }
}
