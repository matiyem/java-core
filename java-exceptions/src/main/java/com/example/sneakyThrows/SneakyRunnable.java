package com.example.sneakyThrows;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 5:45 PM
*/


import lombok.SneakyThrows;

public class SneakyRunnable implements Runnable {
    @SneakyThrows
    public void run() {
        try {
            throw new InterruptedException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {

            new SneakyRunnable().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
