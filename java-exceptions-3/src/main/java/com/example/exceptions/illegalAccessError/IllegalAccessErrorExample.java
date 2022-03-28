package com.example.exceptions.illegalAccessError;

/*
    created by Atiye Mousavi
    Date: 3/23/2022
    Time: 7:03 PM
*/


public class IllegalAccessErrorExample {
    interface Baeldung {
        public default void foobar() {
            System.out.println("This is a default method.");

        }
    }

    class Super {
        private void foobar() {
            System.out.println("SuperClass method foobar");

        }
    }

    class MySubClass extends Super implements Baeldung {
    }
}

