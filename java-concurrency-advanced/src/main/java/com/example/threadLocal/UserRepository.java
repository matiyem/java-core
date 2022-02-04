package com.example.threadLocal;

import java.util.UUID;

/*
    Create by Atiye Mousavi 
    Date: 2/1/2022
    Time: 9:26 AM
**/
public class UserRepository {
    String getUserNameForUserId(Integer userId) {
        return UUID.randomUUID().toString();
    }
}
