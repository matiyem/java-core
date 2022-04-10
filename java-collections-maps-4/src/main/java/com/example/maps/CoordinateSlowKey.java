package com.example.maps;

/*
    Create by Atiye Mousavi 
    Date: 4/9/2022
    Time: 12:33 PM
**/
public class CoordinateSlowKey extends CoordinateKey {

    public CoordinateSlowKey(int x, int y) {
        super(x, y);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
