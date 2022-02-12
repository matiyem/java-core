package com.example.concurrent.semaphores;

import org.apache.commons.lang3.concurrent.TimedSemaphore;

import java.util.concurrent.TimeUnit;

/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 3:52 PM
 **/


public class DelayQueueUsingTimedSemaphore {
//    در ادامه، در مورد Apache Commons TimedSemaphore بحث خواهیم کرد. TimedSemaphore تعدادی مجوز را به عنوان یک Semaphore ساده اجازه می دهد، اما در یک بازه زمانی معین، پس از این مدت زمان بازنشانی و همه مجوزها آزاد می شوند.
//
//می توانیم از TimedSemaphore برای ایجاد یک صف تاخیر ساده به صورت زیر استفاده کنیم:

    private final TimedSemaphore semaphore;

    public DelayQueueUsingTimedSemaphore(long period,int slotLimit) {
        semaphore = new TimedSemaphore(period, TimeUnit.SECONDS,slotLimit);
    }
    boolean tryAdd(){
        return semaphore.tryAcquire();
    }
    int availableSlots(){
        return semaphore.getAvailablePermits();
    }
}
