package com.example.threadSafety.service;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 12:12 PM
**/
public class MessageService {
//    علاوه بر این، اگر MessageService واقعاً قابل تغییر باشد، اما چندین رشته فقط به آن دسترسی خواندنی داشته باشند، ازthread-safe است.
//
//بنابراین، تغییر ناپذیری تنها راه دیگری برای دستیابی به thread-safe است.
    private final String message;

    public MessageService(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
