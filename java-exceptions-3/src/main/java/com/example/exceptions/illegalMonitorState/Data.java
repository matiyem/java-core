package com.example.exceptions.illegalMonitorState;

/*
    created by Atiye Mousavi
    Date: 3/23/2022
    Time: 8:07 PM
*/


public class Data {
    private String message;
    public void send(String message){
        this.message=message;
    }
    public String recive(){
        return message;
    }
}
