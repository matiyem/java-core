package com.example.sneakyThrows;

import java.io.IOException;

/*
    Create by Atiye Mousavi 
    Date: 3/19/2022
    Time: 10:10 AM
**/
public class SneakyThrows {

    public static <E extends Throwable> void sneakyThrow(Throwable e) throws E {
        throw (E) e;
    }

    public static void throwsSneakyIOException() {
        sneakyThrow(new IOException("sneaky"));
    }

    public static void main(String[] args) {
        try {

            throwsSneakyIOException();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
