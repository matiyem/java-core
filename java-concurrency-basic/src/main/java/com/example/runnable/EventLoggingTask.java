package com.example.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 10:11 AM
**/
public class EventLoggingTask implements Runnable {
//    رابط Runnable یک رابط کاربردی است و دارای یک متد ()run است که هیچ پارامتری را نمی پذیرد و هیچ مقداری را بر نمی گرداند.
//    این برای شرایطی مناسب است که ما به دنبال نتیجه اجرای رشته نیستیم، به عنوان مثال، ثبت رویدادهای ورودی:
//    هیچ راهی برای throws کردن exception وجود ندارد
    private Logger logger = LoggerFactory.getLogger(EventLoggingTask.class);


    @Override
    public void run() {
        String message = "Message read from the event queue";
        logger.info("Message read from event queue is " + message);

    }
}
