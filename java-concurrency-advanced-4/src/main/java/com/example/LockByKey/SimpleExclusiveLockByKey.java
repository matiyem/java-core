package com.example.LockByKey;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/*
    Create by Atiye Mousavi 
    Date: 3/15/2022
    Time: 9:44 AM
**/
public class SimpleExclusiveLockByKey {
    private static Set<String> usedKeys= ConcurrentHashMap.newKeySet();

    public boolean tryLock(String key){
        return usedKeys.add(key);
    }

    public void unlock(String key){
        usedKeys.remove(key);
    }
}
