package com.example.concurrent.atomic;

/*
    Create by Atiye Mousavi 
    Date: 1/30/2022
    Time: 3:34 PM
**/
public class SafeCounterWithLock {
//    در مورد یک محیط تک رشته ای، این کاملاً کار می کند. با این حال، به محض اینکه اجازه می دهیم بیش از یک رشته بنویسند، شروع به دریافت نتایج متناقض می کنیم.
//
//این به دلیل عملیات افزایشی ساده (counter++) است که ممکن است شبیه یک عملیات اتمی به نظر برسد، اما در واقع ترکیبی از سه عملیات است: بدست آوردن مقدار، افزایش و نوشتن مقدار به روز شده.
//
//اگر دو رشته سعی کنند مقدار را به طور همزمان دریافت و به روز کنند، ممکن است منجر به از دست رفتن به روز رسانی شود.
//
//    علاوه بر این، ما باید کلمه کلیدی volatile را اضافه کنیم تا از دید مناسب مرجع در بین رشته ها اطمینان حاصل کنیم.
//
//استفاده از قفل مشکل را حل می کند. با این حال، عملکرد ضربه می زند.
//
//هنگامی که چندین رشته سعی می کنند یک قفل را بدست آورند، یکی از آنها برنده می شود، در حالی که بقیه رشته ها یا مسدود می شوند یا معلق می شوند.
//
//فرآیند تعلیق و سپس از سرگیری یک نخ بسیار گران است و بر کارایی کلی سیستم تأثیر می گذارد.
//
//در یک برنامه کوچک، مانند شمارنده، زمان صرف شده در تغییر متن ممکن است بسیار بیشتر از اجرای کد واقعی باشد، بنابراین کارایی کلی را تا حد زیادی کاهش می دهد.
    private volatile int counter;

    int getValue() {
        return counter;
    }

    synchronized void increment() {
        counter++;
    }
}
