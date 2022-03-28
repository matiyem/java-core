package com.example.exceptions.illegalAccessError;

/*
    created by Atiye Mousavi
    Date: 3/23/2022
    Time: 7:02 PM
*/


public class Class2 {
    public void foo(){
        Class1 c1=new Class1();
        c1.bar();
    }
}
