package com.example.threadLocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    Create by Atiye Mousavi 
    Date: 2/1/2022
    Time: 9:36 AM
**/
public class ThreadLocalWithUserContext implements Runnable{
//    ما می توانیم مثال خود را برای ذخیره نمونه Context کاربر با استفاده از ThreadLocal بازنویسی کنیم. هر رشته نمونه ThreadLocal خود را خواهد داشت.
//
//هنگام استفاده از ThreadLocal، باید بسیار مراقب باشیم زیرا هر نمونه ThreadLocal با یک رشته خاص مرتبط است. در مثال ما، برای هر userId خاص یک رشته اختصاصی داریم و این موضوع توسط ما ایجاد شده است، بنابراین کنترل کاملی روی آن داریم.
//
//متد run() زمینه کاربر را واکشی می کند و با استفاده از متد set() در متغیر ThreadLocal ذخیره می کند:
    private static final Logger LOG= LoggerFactory.getLogger(ThreadLocalWithUserContext.class);

    private static final ThreadLocal<Context> userContext=new ThreadLocal<>();
    private final Integer userId;
    private UserRepository userRepository=new UserRepository();

    public ThreadLocalWithUserContext(Integer userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        String userName=userRepository.getUserNameForUserId(userId);
//        در مرحله بعد، وقتی می خواهیم از این مقدار از یک رشته استفاده کنیم، فقط باید یک متد get() یا set() را فراخوانی کنیم. به بیان ساده، می‌توانیم فکر کنیم که ThreadLocal داده‌ها را در داخل map ذخیره می‌کند - با اسم thread به عنوان کلید.
//
//با توجه به این واقعیت، وقتی یک متد get() را در threadLocalValue فراخوانی می کنیم، یک مقدار صحیح برای رشته درخواست کننده دریافت می کنیم:
        userContext.set(new Context(userName));
        LOG.debug("thread context for given userId " + userId + " is: " + userContext.get());
    }
}
