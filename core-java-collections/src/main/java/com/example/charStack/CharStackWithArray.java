package com.example.charStack;

import javax.swing.border.EmptyBorder;
import java.util.EmptyStackException;

/**
 * created by Atiye Mousavi
 * Date: 1/7/2022
 * Time: 4:51 PM
 **/


public class CharStackWithArray {

    private char[] elements;
    private int size;

    public CharStackWithArray() {
        size=0;
        elements=new char[4];
    }
    public int size(){
        return size;
    }
    public char peek(){
        if(size==0){
            throw new EmptyStackException();
        }
        return elements[size-1];
    }
    public char pop(){
        if (size==0){
            throw new EmptyStackException();
        }
        return elements[--size];
    }
    public void push(char item){

    }
    private void ensureCapacity(int newSize){
        char newBiggerArray[];

        if (elements.length <newSize)
    }
}