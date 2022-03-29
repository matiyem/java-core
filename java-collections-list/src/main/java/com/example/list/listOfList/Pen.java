package com.example.list.listOfList;

/*
    created by Atiye Mousavi
    Date: 3/28/2022
    Time: 7:29 PM
*/


public class Pen implements Stationery{
    public String name;

    public Pen(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
