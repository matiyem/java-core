package com.example.list.listOfList;

/*
    created by Atiye Mousavi
    Date: 3/28/2022
    Time: 7:28 PM
*/


public class Pencil implements Stationery {
    public String name;

    public Pencil(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
