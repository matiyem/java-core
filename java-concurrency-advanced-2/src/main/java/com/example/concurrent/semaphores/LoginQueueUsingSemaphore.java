package com.example.concurrent.semaphores;

import java.util.concurrent.Semaphore;

/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 4:19 PM
 **/


public class LoginQueueUsingSemaphore {
    private final Semaphore semaphore;

    LoginQueueUsingSemaphore(int slotLimit) {
        semaphore = new Semaphore(slotLimit);
    }
    boolean tryLogin(){
        return semaphore.tryAcquire();
    }
    void logout(){
        semaphore.release();
    }
    int availableSlots(){
        return semaphore.availablePermits();
    }
}
