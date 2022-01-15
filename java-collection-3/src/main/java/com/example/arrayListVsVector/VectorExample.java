package com.example.arrayListVsVector;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/*
    Create by Atiye Mousavi 
    Date: 1/12/2022
    Time: 2:15 PM
**/
public class VectorExample {
//    به عنوان یک شروع سریع، اجازه دهید تفاوت های کلیدی ArrayList و Vector را ارائه کنیم. سپس، برخی از نکات را با جزئیات بیشتر مورد بحث قرار می دهیم:
//
//synchronization  - اولین تفاوت عمده بین این دو. وکتور هماهنگ است و ArrayList نیست.
//size growth- یکی دیگر از تفاوت های بین این دو، نحوه تغییر اندازه آنها در حین رسیدن به ظرفیت است. Vector اندازه خود را دو برابر می کند. در مقابل، ArrayList فقط نصف طول آن افزایش می یابد
//iteration  – و Vector می تواند از Iterator و Enumeration برای عبور از عناصر استفاده کند. از طرف دیگر، ArrayList فقط می تواند از Iterator استفاده کند.
//performance  - عمدتاً به دلیل همگام سازی، عملیات Vector در مقایسه با ArrayList کندتر است
//performance  – همچنین، ArrayList بخشی از چارچوب مجموعه ها است و در JDK 1.2 معرفی شده است. در همین حال، Vector در نسخه های قبلی جاوا به عنوان یک کلاس قدیمی وجود دارد.
    public static void main(String[] args) {
        Vector<String> vector=new Vector<>();
        vector.add("baeldung");
        vector.add("Vector");
        vector.add("example");

        Enumeration e=vector.elements();
        while (e.hasMoreElements()){
            System.out.println(e.nextElement());
        }
        Iterator<String> iterator= vector.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
