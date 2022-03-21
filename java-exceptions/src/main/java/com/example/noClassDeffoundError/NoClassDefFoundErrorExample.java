package com.example.noClassDeffoundError;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 3:13 PM
*/


public class NoClassDefFoundErrorExample {
    public ClassWithInitErrors getClassWithInitErrors() {
        ClassWithInitErrors test;
        try {

            test = new ClassWithInitErrors();
        }catch (Throwable t){
            System.out.println(t);
        }
        test=new ClassWithInitErrors();
        return test;
    }
}
