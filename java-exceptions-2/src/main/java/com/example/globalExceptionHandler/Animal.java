package com.example.globalExceptionHandler;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 1:49 PM
*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Animal {
}

class Dog extends Animal {

}

class Lion extends Animal {

}

class ClassCast {
    private static Logger LOGGER = LoggerFactory.getLogger(Class.class);

    public static void main(String[] args) {
        try {
            Animal animalOne = new Dog();
            Dog bruno = (Dog) animalOne;

            Animal animalTwo = new Lion();
            Dog tommy = (Dog) animalTwo;
        } catch (Exception e) {
            LOGGER.error("ClassCastException caught!");
        }
    }
}
