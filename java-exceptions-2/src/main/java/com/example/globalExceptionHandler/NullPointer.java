package com.example.globalExceptionHandler;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 4:06 PM
*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NullPointer {
    private static Logger LOGGER = LoggerFactory.getLogger(NullPointer.class);

    public static void main(String[] args) {
        Person personObj = null;

        try {
            String name = personObj.personName;
            personObj.personName = "Jon Doe";
        } catch (Exception e) {
            LOGGER.error("NullPointerException caught!");
        }
    }
}

class Person {
    public String personName;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
