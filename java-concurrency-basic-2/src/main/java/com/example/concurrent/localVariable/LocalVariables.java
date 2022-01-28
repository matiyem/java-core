package com.example.concurrent.localVariable;

import java.security.SecureRandom;

/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 12:41 PM
**/
public class LocalVariables implements Runnable {
//    بیایید با یک خلاصه نویسی سریع از مدل حافظه JVM شروع کنیم.
//
//مهمتر از همه، JVM حافظه موجود خود را به حافظه stack و heap تقسیم می کند. در مرحله اول، تمام اشیاء را روی heap ذخیره می کند. ثانیاً، منابع اولیه محلی و ارجاعات شیء محلی را در stack ذخیره می کند.
//
//علاوه بر این، درک این نکته مهم است که هر رشته، از جمله رشته اصلی، stack خصوصی خود را دارد. بنابراین، رشته‌های دیگر متغیرهای محلی ما را به اشتراک نمی‌گذارند، و این همان چیزی است که آنها را ایمن می‌کند.


//    در خط پنج، یک کپی از کلاس LocalVariables را نمونه سازی می کنیم. در دو خط بعدی، دو رشته را شروع می کنیم. هر دو روش اجرای یک نمونه را اجرا می کنند.
//
//داخل متد run، فیلد کلاس LocalVariables را به روز می کنیم. ثانیاً، ما یک انتساب به یک بدوی محلی را می بینیم. در نهایت دو فیلد را در کنسول چاپ می کنیم.
//
//بیایید نگاهی به مکان حافظه همه فیلدها بیندازیم.
//
//اول، فیلد فیلدی از کلاس LocalVariables است. بنابراین، روی heap زندگی می کند. ثانیاً، عدد متغیر محلی یک عدد ابتدایی است. در نتیجه، در stack قرار دارد.
//
//عبارت println جایی است که ممکن است هنگام اجرای دو رشته، همه چیز به اشتباه پیش برود.
//
//اولاً، فیلد فیلد احتمال زیادی برای ایجاد مشکل دارد زیرا هم reference و هم object روی heap زندگی می‌کنند و بین رشته‌های ما به اشتراک گذاشته می‌شوند. محلی بدوی مشکلی ندارد زیرا ارزش روی پشته باقی می‌ماند. در نتیجه، JVM محلی را بین رشته ها به اشتراک نمی گذارد.
//
//بنابراین هنگام اجرا می توانیم، برای مثال، خروجی زیر را داشته باشیم:
    private int field;

    public static void main(String[] args) {
        LocalVariables target=new LocalVariables();
        new Thread(target).start();
        new Thread(target).start();
    }
    @Override
    public void run() {
        field=new SecureRandom().nextInt();
        int local=new SecureRandom().nextInt();
        System.out.println(field + " - " + local);

    }
}
