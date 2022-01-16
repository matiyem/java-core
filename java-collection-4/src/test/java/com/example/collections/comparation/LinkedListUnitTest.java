package com.example.collections.comparation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class LinkedListUnitTest {
//    LinkedList یک پیاده سازی لیست با پیوند دوگانه است. پیاده سازی هر دو رابط List و Deque (توسعه ای از Queue). برخلاف ArrayList، وقتی داده‌ها را در LinkedList ذخیره می‌کنیم، هر عنصر پیوندی را به عنصر قبلی حفظ می‌کند.
//
//علاوه بر روش‌های استاندارد درج لیست، LinkedList از روش‌های دیگری پشتیبانی می‌کند که می‌توانند یک عنصر را در ابتدای انتهای لیست اضافه کنند:

//    یک LinkedList کمی بیشتر از یک ArrayList حافظه مصرف می کند زیرا هر گره دو مرجع به عنصر قبلی و بعدی ذخیره می کند.
//
//عملیات درج، افزودن و حذف در LinkedList سریعتر است زیرا تغییر اندازه آرایه در پس‌زمینه انجام نشده است. هنگامی که یک مورد جدید در جایی در وسط لیست اضافه می شود، فقط مراجع در عناصر اطراف باید تغییر کنند.
//    بیشتر اوقات می‌توانیم از ArrayList به عنوان پیاده‌سازی فهرست پیش‌فرض استفاده کنیم. با این حال، در موارد خاص، باید از LinkedList استفاده کنیم. این موارد شامل زمانی است که زمان درج و حذف ثابت، زمان دسترسی ثابت و استفاده موثر از حافظه را ترجیح می دهیم.
//
//استفاده از LinkedList زمانی منطقی است که ترتیب موارد یکسان را حفظ کنید و زمان درج سریع (افزودن و حذف موارد در هر موقعیتی) یک معیار مهم است.
//
//مانند ArrayList، زمانی که ترتیب آیتم ها مهم نیست، باید از استفاده از LinkedList اجتناب کنیم. هنگامی که زمان دسترسی سریع یا جستجوی موارد یک نیاز مهم است، LinkedList بهترین گزینه نیست

    @Test
    void givenLinkedList_whenItemIsAppended_thenItCanBeRetrieved() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("Daniel");
        list.addFirst("Marko");
        assertThat(list).hasSize(2);
        assertThat(list.getLast()).isEqualTo("Daniel");
    }

    @Test
    void givenLinkedList_whenItemIsRemoved_thenListSizeIsReduced() {
        LinkedList<String> list = new LinkedList<>(Arrays.asList("Daniel", "Marko", "David"));
        list.removeFirst();
        list.removeLast();
        assertThat(list).hasSize(1);
        assertThat(list).containsExactly("Marko");
    }

    @Test
    void givenLinkedList_whenItemInserted_thenItCanBeRetrievedAndDeleted() {
        LinkedList<String> list = new LinkedList<>();
        list.push("Daniel");
        list.push("Marko");
        assertThat(list.poll()).isEqualTo("Marko");
        assertThat(list).hasSize(1);
    }

}
