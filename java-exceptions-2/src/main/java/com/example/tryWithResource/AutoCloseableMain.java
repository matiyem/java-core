package com.example.tryWithResource;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 7:36 PM
*/


public class AutoCloseableMain {
    public static void main(String[] args) throws Exception {
        orderOfClosingResources();
    }
    private static void orderOfClosingResources() throws Exception {
//        به عبارت ساده، برای بسته شدن خودکار، یک منبع باید هم اعلام شود و هم در داخل try مقداردهی اولیه شود:
        try (AutoCloseableResourcesFirst af = new AutoCloseableResourcesFirst();
             AutoCloseableResourcesSecond as = new AutoCloseableResourcesSecond()) {
            af.doSomething();
            as.doSomething();
        }
    }
}
