package com.example.LockByKey;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

/*
    Create by Atiye Mousavi 
    Date: 3/15/2022
    Time: 10:18 AM
**/
public class SimultaneousEntriesLockByKey {
    private static final int ALLOWED_THREADS = 2;

    private static ConcurrentHashMap<String, Semaphore> semaphores=new ConcurrentHashMap<>();

    public void lock(String key){
        Semaphore semaphore=semaphores.compute(key , (k,v) -> v== null ? new Semaphore(ALLOWED_THREADS) : v);
        semaphore.acquireUninterruptibly();
    }
    public void unlock(String key){
        Semaphore semaphore=semaphores.get(key);
        semaphore.release();
        if (semaphore.availablePermits() == ALLOWED_THREADS){
            semaphores.remove(key,semaphore);
        }
    }
}
