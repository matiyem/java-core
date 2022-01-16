package com.example.dequestack;


import java.util.Collection;

/*
    Create by Atiye Mousavi 
    Date: 1/16/2022
    Time: 9:32 AM
**/
public interface LifoStack<E> extends Collection<E> {

    E peek();

    E pop();

    void push(E item);
}
