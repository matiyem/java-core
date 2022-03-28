package com.example.exceptions.noSuchmethodError;

/*
    created by Atiye Mousavi
    Date: 3/24/2022
    Time: 12:10 PM
*/


public class MainMenu {
    public static void main(String[] args) {
        System.out.println("Today's Specials: " + getSpecials());
    }

    public static String getSpecials() {
        return SpecialToday.getDesert();
    }
}
