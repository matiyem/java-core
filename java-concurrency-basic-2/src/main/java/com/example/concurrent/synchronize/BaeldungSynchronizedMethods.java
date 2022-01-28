package com.example.concurrent.synchronize;

/*
    Create by Atiye Mousavi 
    Date: 1/24/2022
    Time: 4:16 PM
**/
public class BaeldungSynchronizedMethods {
//به بیان ساده، در یک محیط چند رشته ای، یک شرط مسابقه زمانی رخ می دهد که دو یا چند رشته سعی می کنند داده های مشترک قابل تغییر را به طور همزمان به روز کنند. جاوا مکانیزمی را برای جلوگیری از شرایط مسابقه با همگام سازی دسترسی رشته به داده های مشترک ارائه می دهد.
//
//یک قطعه منطق که با synchronized علامت گذاری شده است به یک بلوک همگام تبدیل می شود که به تنها یک رشته اجازه می دهد در هر زمان معین اجرا شود.
//    ما می توانیم از کلمه کلیدی همگام سازی شده در سطوح مختلف استفاده کنیم:
//
//Instance methods
//Static methods
//Code blocks
//هنگامی که ما از یک بلوک همگام استفاده می کنیم، جاوا به صورت داخلی از یک نمایشگر استفاده می کند که به عنوان قفل مانیتور یا قفل ذاتی نیز شناخته می شود، تا همگام سازی را فراهم کند. این مانیتورها به یک شی متصل هستند. بنابراین، همه بلوک‌های همگام‌شده یک شی می‌توانند تنها یک رشته داشته باشند که آنها را همزمان اجرا می‌کند.

    private int sum = 0;
    private int syncSum = 0;

   public static int staticSum = 0;

   public void calculate() {
        setSum(getSum() + 1);
    }
//می‌توانیم کلمه کلیدی همگام‌سازی شده را در اعلان متد اضافه کنیم تا روش همگام شود:
    public synchronized void synchronisedCalculate() {
        setSyncSum(getSyncSum() + 1);
    }
//روش‌های استاتیک درست مانند روش‌های نمونه همگام‌سازی می‌شوند:
//    این متدها روی شی Class مرتبط با کلاس همگام می شوند. از آنجایی که فقط یک شی Class در هر JVM در هر کلاس وجود دارد، صرف نظر از تعداد نمونه‌هایی که دارد، فقط یک رشته می‌تواند در یک روش همگام‌سازی استاتیک در هر کلاس اجرا شود.
//
//بیایید آن را آزمایش کنیم:
    public static synchronized void syncStaticCalculate() {
        staticSum = staticSum + 1;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getSyncSum() {
        return syncSum;
    }

    public void setSyncSum(int syncSum) {
        this.syncSum = syncSum;
    }

    public static int getStaticSum() {
        return staticSum;
    }

    public static void setStaticSum(int staticSum) {
        BaeldungSynchronizedMethods.staticSum = staticSum;
    }
}
