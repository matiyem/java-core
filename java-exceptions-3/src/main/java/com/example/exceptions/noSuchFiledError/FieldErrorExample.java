package com.example.exceptions.noSuchFiledError;

/*
    created by Atiye Mousavi
    Date: 3/24/2022
    Time: 12:08 PM
*/


public class FieldErrorExample {
    public static void main(String[] args) {
        fetchAndPrint();
    }
    public static String getDependentMessage(){
        return Dependency.message;
    }
    public static void fetchAndPrint(){
        System.out.println(getDependentMessage());
    }
}
