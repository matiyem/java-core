package com.example.exceptions;

/*
    created by Atiye Mousavi
    Date: 3/22/2022
    Time: 11:22 AM
*/


import java.util.Set;
import java.util.UUID;

public class CatchingThrowable {

    class CapacityException extends Exception {
        CapacityException(String message) {
            super(message);
        }
    }

    class StorageAPI {
        public void addIDsToStorage(int capacity, Set<String> storage) throws CapacityException {
            if (capacity < 1) {
                throw new CapacityException("Capacity of less then 1 is not allowed ");
            }
            int count = 0;
            while (count < capacity) {
                storage.add(UUID.randomUUID().toString());
                count++;
            }
        }
    }

    public void add(StorageAPI api, int capacity, Set<String> storage) {
        try {
            api.addIDsToStorage(capacity, storage);
        } catch (Throwable throwable){

        }

    }
}
