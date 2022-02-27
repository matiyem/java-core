package com.example.thisScape;

/*
    Create by Atiye Mousavi 
    Date: 2/16/2022
    Time: 10:24 AM
**/
public class ImplicitEscape {
//    همانطور که در بالا نشان داده شد، ما یک کلاس داخلی ناشناس ایجاد می کنیم که از Thread مشتق شده است. از آنجایی که کلاس های داخلی یک مرجع به کلاس محصور خود حفظ می کنند، این مرجع دوباره از سازنده فرار می کند.
//    هیچ مشکلی با ایجاد Thread در داخل سازنده وجود ندارد. با این حال، شروع فوری آن بسیار دلسرد می شود، زیرا در بیشتر مواقع، به طور صریح یا ضمنی با یک مرجع فرار از این مرجع مواجه می شویم.
    public ImplicitEscape() {
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("started...");
            }
        };
        t.start();

    }
}
