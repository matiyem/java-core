package com.example.keyword.finalize;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 2:23 PM
*/


public class FinalizeObject {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Execute finalize method");
        super.finalize();
    }

    public static void main(String[] args) throws InterruptedException {
        FinalizeObject object=new FinalizeObject();
        object=null;
        System.gc();
        Thread.sleep(1000);
    }
}
