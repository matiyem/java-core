package com.example.mutex;

import java.util.concurrent.locks.ReentrantLock;

/*
    Create by Atiye Mousavi 
    Date: 1/29/2022
    Time: 4:28 PM
**/
public class SequenceGeneratorUsingReentrantLock extends SequenceGenerator {
//    کلاس ReentrantLock در جاوا 1.5 معرفی شد. انعطاف پذیری و کنترل بیشتری را نسبت به رویکرد کلمات کلیدی همگام شده فراهم می کند.
    private ReentrantLock mutex = new ReentrantLock();

    @Override
    public int getNextSequence() {
        try {

            mutex.lock();
            return super.getNextSequence();
        }finally {
            mutex.unlock();
        }

    }
}
