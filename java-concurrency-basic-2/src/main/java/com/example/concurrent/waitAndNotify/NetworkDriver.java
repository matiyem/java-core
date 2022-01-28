package com.example.concurrent.waitAndNotify;

/*
    Create by Atiye Mousavi 
    Date: 1/25/2022
    Time: 11:15 AM
**/
public class NetworkDriver {
    public static void main(String[] args) {
        Data data=new Data();
        Thread sender=new Thread(new Sender(data));
        Thread reciver=new Thread(new Receiver(data));

        sender.start();
        reciver.start();
    }
}
