package com.example.concurrent.sleepWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 12:52 PM
**/
public class WaitSleepExample {
//    به زبان ساده، wait() یک روش نمونه است که برای همگام سازی رشته ها استفاده می شود.
//
//همانطور که در java.lang.Object تعریف شده است، می توان آن را بر روی هر شیئی فراخوانی کرد، اما فقط می توان آن را از یک بلوک همزمان فراخوانی کرد. قفل روی شی را آزاد می کند تا نخ دیگری بتواند به داخل بپرد و قفلی را بدست آورد.
//
//از سوی دیگر، Thread.sleep() یک متد ثابت است که می تواند از هر زمینه ای فراخوانی شود. () Thread.sleep رشته فعلی را متوقف می کند و هیچ قفلی را آزاد نمی کند.
//
//در اینجا یک نگاه اولیه بسیار ساده به این دو API اصلی در عمل آورده شده است:
    private static final Logger LOG = LoggerFactory.getLogger(WaitSleepExample.class);

    private static final Object Lock=new Object();

    public static void main(String... args) throws InterruptedException {
        sleepWaitInSynchronizedBlocks();
    }
    private static void sleepWaitInSynchronizedBlocks() throws InterruptedException {
        Thread.sleep(1000);
        LOG.debug("Thread '" + Thread.currentThread().getName() + "' is woken after sleeping for 1 second");

        synchronized (Lock){
            Lock.wait(1000);
            LOG.debug("Object '" + Lock+ "' is woken after waiting for 1 second");
        }
    }

}
