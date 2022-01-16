package com.example.collections.dequestack;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;

class StackVsDequeUnitTest {
//    ما دیدیم که کلاس Stack یک زیر کلاس از java.util.Vector است. کلاس Vector همگام شده است. از روش سنتی برای دستیابی به ایمنی نخ استفاده می‌کند: «همگام‌سازی» روش‌های آن.
//
//به عنوان زیر کلاس خود، کلاس Stack نیز هماهنگ می شود.
//
//از طرف دیگر، رابط Deque امن نیست.
//
//بنابراین، اگر ایمنی نخ یک الزام نباشد، یک Deque می تواند عملکرد بهتری نسبت به Stack برای ما به ارمغان بیاورد.
//    با این حال، آنچه جالب است این است که اگر عناصر یکسان را با ترتیب یکسان به یک شی Stack و یک نمونه Deque فشار دهیم، ترتیب تکرار آنها متفاوت است.
//    بنابراین، ترتیب تکرار Deque از بالا به پایین است.
//
//هنگامی که ما در مورد ساختار داده پشته LIFO صحبت می کنیم، ترتیب صحیح تکرار روی عناصر در پشته باید از بالا به پایین باشد.
//
//یعنی تکرار کننده Deque همانطور که ما برای یک پشته انتظار داریم کار می کند
//    یک ساختار داده کلاسیک پشته LIFO فقط از عملیات push()، pop() و peek() پشتیبانی می کند. هم کلاس Stack و هم رابط Deque از آنها پشتیبانی می کنند. تا کنون خیلی خوب.
//
//با این حال، دسترسی یا دستکاری عناصر توسط شاخص های موجود در پشته LIFO مجاز نیست زیرا قانون LIFO را زیر پا می گذارد.
//
//در این بخش، بیایید ببینیم که آیا می توانیم عملیات پشته نامعتبر را با Stack و Deque فراخوانی کنیم.

    @Test
    void givenAStack_whenAccessByIndex_thenElementCanBeRead() {
//        از آنجایی که Vector والد آن یک ساختار داده مبتنی بر آرایه است، کلاس Stack این توانایی را دارد که به عناصر توسط ایندکس ها دسترسی پیدا کند:
        Stack<String> myStack = new Stack<>();
        myStack.push("I am the 1st element."); //index 0
        myStack.push("I am the 2nd element."); //index 1
        myStack.push("I am the 3rd element."); //index 2
        //access by index
        assertThat(myStack.get(0)).isEqualTo("I am the 1st element.");
    }

    @Test
    void givenAStack_whenIterate_thenFromBottomToTop() {
        Stack<String> myStack = new Stack<>();
        myStack.push("I am at the bottom.");
        myStack.push("I am in the middle.");
        myStack.push("I am at the top.");

        Iterator<String> it = myStack.iterator();

        assertThat(it).toIterable().containsExactly(
          "I am at the bottom.",
          "I am in the middle.",
          "I am at the top.");
    }

    @Test
    void givenAStack_whenAddOrRemoveByIndex_thenElementCanBeAddedOrRemoved() {
        Stack<String> myStack = new Stack<>();
        myStack.push("I am the 1st element.");
        myStack.push("I am the 3rd element.");

        assertThat(myStack.size()).isEqualTo(2);

        //insert by index
        myStack.add(1, "I am the 2nd element.");
        assertThat(myStack.size()).isEqualTo(3);
        assertThat(myStack.get(1)).isEqualTo("I am the 2nd element.");
        //remove by index
        myStack.remove(1);
        assertThat(myStack.size()).isEqualTo(2);
    }

    @Test
    void givenADeque_whenAddOrRemoveLastElement_thenTheLastElementCanBeAddedOrRemoved() {
//        در تست، ابتدا یک عنصر جدید را با استفاده از متد addLast() در انتهای یک پشته قرار می دهیم. در صورت موفقیت آمیز بودن درج، سعی می کنیم آن را با متد removeLast() حذف کنیم.
//
//اگر تست را اجرا کنیم، قبول می شود.
//
//بنابراین، Deque از قرارداد LIFO نیز تبعیت نمی کند.
        Deque<String> myStack = new ArrayDeque<>();
        myStack.push("I am the 1st element.");
        myStack.push("I am the 2nd element.");
        myStack.push("I am the 3rd element.");

        assertThat(myStack.size()).isEqualTo(3);

        //insert element to the bottom of the stack
        myStack.addLast("I am the NEW element.");
        assertThat(myStack.size()).isEqualTo(4);
        assertThat(myStack.peek()).isEqualTo("I am the 3rd element.");

        //remove element from the bottom of the stack
        String removedStr = myStack.removeLast();
        assertThat(myStack.size()).isEqualTo(3);
        assertThat(removedStr).isEqualTo("I am the NEW element.");
    }

    @Test
    void givenADeque_whenIterate_thenFromTopToBottom() {
        Deque<String> myStack = new ArrayDeque<>();
        myStack.push("I am at the bottom.");
        myStack.push("I am in the middle.");
        myStack.push("I am at the top.");

        Iterator<String> it = myStack.iterator();

        assertThat(it).toIterable().containsExactly(
          "I am at the top.",
          "I am in the middle.",
          "I am at the bottom.");
    }
}