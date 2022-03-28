package com.example.exceptions.classCastException;

/*
    created by Atiye Mousavi
    Date: 3/23/2022
    Time: 6:15 PM
*/


public class Box<T> {
    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
