package com.example.mutex;

/*
    Create by Atiye Mousavi 
    Date: 1/29/2022
    Time: 4:33 PM
**/
public class SequenceGeneratorUsingSynchronizedBlock extends SequenceGenerator {
//    بلوک همگام‌سازی شده مشابه روش همگام‌سازی شده است، با کنترل بیشتر بر بخش بحرانی و شیئی که می‌توانیم برای قفل کردن استفاده کنیم.
    private Object mutex = new Object();

    @Override
    public int getNextSequence() {
        synchronized (mutex) {
            return super.getNextSequence();
        }
    }
}
