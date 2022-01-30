package com.example.mutex;

/*
    Create by Atiye Mousavi 
    Date: 1/29/2022
    Time: 4:34 PM
**/
public class SequenceGeneratorUsingSynchronizedMethod extends SequenceGenerator{
//ابتدا، کلمه کلیدی همگام سازی شده را مورد بحث قرار می دهیم، که ساده ترین راه برای پیاده سازی mutex در جاوا است.
//
//هر شی در جاوا دارای یک قفل ذاتی مرتبط با آن است. روش همگام و بلوک همگام‌سازی شده از این قفل ذاتی برای محدود کردن دسترسی به بخش بحرانی فقط به یک رشته در یک زمان استفاده می‌کنند.
//
//بنابراین، هنگامی که یک نخ یک روش همگام را فراخوانی می کند یا وارد یک بلوک همگام می شود، به طور خودکار قفل را می گیرد. زمانی که متد یا بلوک کامل شود یا یک استثنا از آنها پرتاب شود، قفل آزاد می شود.
//
//بیایید getNextSequence را به یک mutex تغییر دهیم، به سادگی با افزودن کلمه کلیدی همگام‌سازی شده:
    @Override
    public int getNextSequence() {
        return super.getNextSequence();
    }
}
