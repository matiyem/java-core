package com.example.maps;

import java.util.Objects;

/*
    Create by Atiye Mousavi 
    Date: 4/9/2022
    Time: 12:10 PM
**/
public class CoordinateKey {
    private final int x;
    private final int y;
    private final int hashCode;

    public CoordinateKey(int x, int y) {
        this.x = x;
        this.y = y;
        this.hashCode = Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CoordinateKey that = (CoordinateKey) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}
