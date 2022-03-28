package com.example.exceptions.illegalAccessError;

/*
    created by Atiye Mousavi
    Date: 3/23/2022
    Time: 7:09 PM
*/


public class IllegalAccessErrorSolved {
    interface BaeldungSolved {
        public default void foobar() {
            System.out.println("This is a default method.");
        }
    }

    class SuperSolved {
        public void foobar() {
            System.out.println("SuperClass method foobar");
        }
    }

    class MySubClassSolved extends SuperSolved implements BaeldungSolved {
    }
}
