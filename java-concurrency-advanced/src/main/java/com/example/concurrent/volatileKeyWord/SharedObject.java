package com.example.concurrent.volatileKeyWord;

/*
    Create by Atiye Mousavi 
    Date: 2/1/2022
    Time: 9:17 AM
**/
public class SharedObject {
//    در غیاب همگام‌سازی‌های لازم، کامپایلر، زمان اجرا یا پردازنده‌ها ممکن است انواع بهینه‌سازی‌ها را اعمال کنند. حتی اگر این بهینه‌سازی‌ها در بیشتر مواقع سودمند هستند، گاهی اوقات می‌توانند مشکلات ظریفی ایجاد کنند.
//
//ذخیره سازی و مرتب سازی مجدد از جمله بهینه سازی هایی هستند که ممکن است در زمینه های همزمان ما را شگفت زده کنند. جاوا و JVM راه های زیادی را برای کنترل ترتیب حافظه ارائه می دهند و کلمه کلیدی volatile یکی از آنهاست.
//
//در این مقاله، ما بر روی این مفهوم اساسی اما اغلب اشتباه در زبان جاوا - کلمه کلیدی volatile - تمرکز خواهیم کرد. ابتدا، با کمی پیش زمینه در مورد نحوه عملکرد معماری کامپیوتری زیرین شروع می کنیم و سپس با ترتیب حافظه در جاوا آشنا می شویم.

//    پردازنده ها مسئول اجرای دستورالعمل های برنامه هستند. بنابراین، آنها باید هم دستورالعمل های برنامه و هم داده های مورد نیاز را از رم بازیابی کنند.
//
//از آنجایی که CPU ها قادر به انجام تعداد قابل توجهی دستورات در ثانیه هستند، واکشی از RAM برای آنها ایده آل نیست. برای بهبود این وضعیت، پردازنده ها از ترفندهایی مانندExecution, Branch Prediction, Speculative Execution, and, of course, Caching. استفاده می کنند.
//
//اینجا جایی است که سلسله مراتب حافظه زیر وارد عمل می شود:

//    همانطور که هسته های مختلف دستورالعمل های بیشتری را اجرا می کنند و داده های بیشتری را دستکاری می کنند، حافظه پنهان خود را با داده ها و دستورالعمل های مرتبط تری پر می کنند. این کار عملکرد کلی را به قیمت معرفی چالش‌های انسجام حافظه پنهان بهبود می‌بخشد.
//
//به زبان ساده، ما باید دو بار در مورد اینکه چه اتفاقی می افتد زمانی که یک رشته یک مقدار ذخیره شده را به روز می کند، فکر کنیم.
    private volatile int count = 0;

    public void incrementCount() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
