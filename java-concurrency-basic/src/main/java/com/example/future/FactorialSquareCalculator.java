package com.example.future;

import java.util.concurrent.RecursiveTask;

/*
    Create by Atiye Mousavi 
    Date: 1/17/2022
    Time: 12:53 PM
**/
public class FactorialSquareCalculator extends RecursiveTask<Integer> {
//    ForkJoinTask یک کلاس انتزاعی است که Future را پیاده سازی می کند و می تواند تعداد زیادی کار را که توسط تعداد کمی از رشته های واقعی در ForkJoinPool میزبانی می شوند، اجرا کند.
//
//در این بخش به سرعت به ویژگی های اصلی ForkJoinPool می پردازیم. برای یک راهنمای جامع در مورد موضوع، راهنمای ما برای چارچوب فورک/پیوستن در جاوا را بررسی کنید.
//
//ویژگی اصلی ForkJoinTask این است که معمولاً وظایف فرعی جدیدی را به عنوان بخشی از کار مورد نیاز برای تکمیل وظیفه اصلی خود ایجاد می کند. با فراخوانی fork() وظایف جدیدی ایجاد می کند و همه نتایج را با join() جمع آوری می کند، بنابراین نام کلاس.
//
//دو کلاس انتزاعی وجود دارد که ForkJoinTask را پیاده‌سازی می‌کنند: RecursiveTask که پس از اتمام یک مقدار را برمی‌گرداند و RecursiveAction که چیزی را بر نمی‌گرداند. همانطور که از نام آنها پیداست، این کلاس ها برای کارهای بازگشتی مانند ناوبری سیستم فایل یا محاسبات پیچیده ریاضی استفاده می شوند.
//
//بیایید مثال قبلی خود را گسترش دهیم تا کلاسی ایجاد کنیم که با یک عدد صحیح مجذور مجذورات را برای تمام عناصر فاکتوریل خود محاسبه کند. به عنوان مثال، اگر عدد 4 را به ماشین حساب خود منتقل کنیم، باید از مجموع 4² + 3² + 2² + 1² که 30 می شود، نتیجه را بدست آوریم.
//
//ابتدا باید یک پیاده سازی مشخص از RecursiveTask ایجاد کنیم و متد compute() آن را پیاده سازی کنیم. اینجا جایی است که منطق کسب و کار خود را می نویسیم:


    private static final long serialVersionUID = 1L;

    final private Integer n;

    public FactorialSquareCalculator(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        FactorialSquareCalculator calculator = new FactorialSquareCalculator(n - 1);
        calculator.fork();
        return n * n + calculator.join();
    }
}
