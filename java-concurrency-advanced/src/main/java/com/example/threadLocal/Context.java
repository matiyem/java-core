package com.example.threadLocal;

/*
    Create by Atiye Mousavi 
    Date: 2/1/2022
    Time: 9:23 AM
**/
public class Context {
    private final String userName;

    public Context(String userName) {
        this.userName = userName;
    }
    @Override
    public String toString() {
        return "Context{" +
                "userNameSecret='" + userName + '\'' +
                '}';
    }
}
