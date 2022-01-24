package com.example.executor;

import java.util.concurrent.Executor;

/*
    Create by Atiye Mousavi 
    Date: 1/17/2022
    Time: 11:37 AM
**/
public class Invoker implements Executor {
//    Executor یک رابط است که نشان دهنده یک شی است که وظایف ارائه شده را اجرا می کند.
//
//این بستگی به اجرای خاص (از جایی که فراخوانی شروع می شود) دارد که آیا وظیفه باید روی یک رشته جدید یا فعلی اجرا شود. از این رو، با استفاده از این رابط، می‌توانیم جریان اجرای وظیفه را از مکانیزم اجرای وظیفه واقعی جدا کنیم.
//
//یکی از نکاتی که در اینجا باید به آن توجه کرد این است که Executor اکیداً نیازی به ناهمزمان بودن اجرای وظیفه ندارد. در ساده ترین حالت، یک مجری می تواند وظیفه ارسال شده را فوراً در رشته فراخوانی فراخوانی کند.
//
//برای ایجاد نمونه executor باید یک Invoker ایجاد کنیم:
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
