package com.example.concurrent.future;

import com.example.future.FactorialSquareCalculator;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.assertEquals;

public class FactorialSquareCalculatorUnitTest {
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


// توجه کنید که چگونه با ایجاد یک نمونه جدید از FactorialSquareCalculator در compute() به بازگشتی دست پیدا می کنیم. با فراخوانی fork()، یک روش غیر مسدود کننده، از ForkJoinPool می خواهیم تا اجرای این زیرکار را آغاز کند.
//
//متد join() نتیجه آن محاسبه را برمی گرداند، که مربع عددی را که در حال حاضر بازدید می کنیم به آن اضافه می کنیم.
//
//اکنون فقط باید یک ForkJoinPool ایجاد کنیم تا مدیریت اجرا و رشته را مدیریت کند:
    @Test
    public void whenCalculatesFactorialSquare_thenReturnCorrectValue() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        FactorialSquareCalculator calculator = new FactorialSquareCalculator(10);

        forkJoinPool.execute(calculator);

        assertEquals("The sum of the squares from 1 to 10 is 385", 385, calculator.join().intValue());
    }

}
