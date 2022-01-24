package com.example.threadSafety.callables;

import com.example.threadSafety.service.MessageService;

import java.util.concurrent.Callable;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 1:08 PM
**/
public class MessageServiceCallable implements Callable<String> {

    private final MessageService messageService;

    public MessageServiceCallable(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public String call() throws Exception {
        return messageService.getMessage();
    }
}
