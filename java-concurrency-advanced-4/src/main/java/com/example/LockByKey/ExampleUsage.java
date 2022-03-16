package com.example.LockByKey;

/*
    Create by Atiye Mousavi 
    Date: 3/15/2022
    Time: 9:43 AM
**/
public class ExampleUsage {

    void doWithSimpleExclusiveLock(String key) {
        SimpleExclusiveLockByKey simpleExclusiveLockByKey = new SimpleExclusiveLockByKey();
        if (simpleExclusiveLockByKey.tryLock(key)) {
            try {
                //do stuff
            } finally {
                simpleExclusiveLockByKey.unlock(key);
            }
        }
    }

    void doWithLock(String key) {
        LockByKey lockByKey = new LockByKey();
        lockByKey.lock(key);
        try {
            //do stuff
        } finally {
            lockByKey.unlock(key);
        }
    }

    void doWithSemaphore(String key) {
        SimultaneousEntriesLockByKey lockByKey = new SimultaneousEntriesLockByKey();
        lockByKey.lock(key);
        try {
            //do stuff
        } finally {
            lockByKey.unlock(key);
        }
    }
}
