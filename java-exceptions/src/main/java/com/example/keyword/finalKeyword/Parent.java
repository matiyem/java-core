package com.example.keyword.finalKeyword;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 2:27 PM
*/


public class Parent {
    int field1=1;
    final int field2=2;
    Parent(){
        field1=2;
    }
    void method1(int arg1,final int arg2){
        arg1=2;
    }
    final void method2(){
        final int localVar=2;
    }
}
