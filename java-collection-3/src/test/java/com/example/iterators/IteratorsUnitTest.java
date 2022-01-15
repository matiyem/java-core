package com.example.iterators;

import org.junit.Test;

import java.util.ConcurrentModificationException;

import static com.example.iterators.Iterators.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Source code https://github.com/eugenp/tutorials
 *
 * @author Santosh Thakur
 */

public class IteratorsUnitTest {
//    در این مقاله مفهوم Fail-Fast و Fail-Safe Iterators را معرفی می کنیم.
//
//سیستم‌های Fail-Fast عملیات را در سریع‌ترین زمان ممکن متوقف می‌کنند و فوراً خرابی‌ها را آشکار می‌کنند و کل عملیات را متوقف می‌کنند.
//
//در حالی که سیستم های Fail-Safe در صورت خرابی عملیات را متوقف نمی کنند. چنین سیستم هایی سعی می کنند تا حد امکان از افزایش خرابی ها جلوگیری کنند.

//    هنگامی که مجموعه زیربنایی اصلاح می شود، تکرار کننده های سریع در جاوا پخش نمی شوند.
//
//مجموعه ها یک شمارنده داخلی به نام modCount دارند. هر بار که یک مورد از مجموعه اضافه یا حذف می شود، این شمارنده افزایش می یابد.
//
//هنگام تکرار، در هر فراخوانی () بعدی، مقدار فعلی modCount با مقدار اولیه مقایسه می شود. اگر عدم تطابق وجود داشته باشد، ConcurrentModificationException را پرتاب می کند که کل عملیات را لغو می کند.
//
//تکرار کننده های پیش فرض مجموعه های بسته java.util مانند ArrayList، HashMap و غیره Fail-Fast هستند.

//    تکرارکننده‌های Fail-Safe فقدان شکست را به جای ناراحتی در رسیدگی به استثنا ترجیح می‌دهند.
//
//این تکرار کننده ها یک کلون از مجموعه واقعی ایجاد می کنند و روی آن تکرار می کنند. اگر پس از ایجاد تکرار کننده هر گونه تغییری رخ دهد، کپی همچنان دست نخورده باقی می ماند. از این رو، این تکرارکننده‌ها به چرخش در مجموعه ادامه می‌دهند، حتی اگر اصلاح شده باشد.
//
//با این حال، مهم است که به یاد داشته باشید که چیزی به نام تکرار کننده واقعی Fail-Safe وجود ندارد. عبارت صحیح Weakly Consistent است.
//
//این بدان معناست که اگر مجموعه‌ای در حین تکرار اصلاح شود، آنچه Iterator می‌بیند تضمین ضعیفی دارد. این رفتار ممکن است برای مجموعه‌های مختلف متفاوت باشد و در Javadocs هر یک از این مجموعه‌ها مستند شده است.
//
//با این حال، Fail-Safe Iterators دارای چند معایب است. یکی از معایب این است که Iterator تضمینی برای بازگرداندن داده های به روز شده از مجموعه ندارد، زیرا به جای مجموعه واقعی روی کلون کار می کند.
//
//یکی دیگر از معایب سربار ایجاد یک کپی از مجموعه، هم از نظر زمان و هم از نظر حافظه است.
//
//تکرارکننده‌ها در مجموعه‌های بسته java.util.concurrent مانند ConcurrentHashMap، CopyOnWriteArrayList و غیره ماهیت Fail-Safe دارند.

    @Test
    public void whenFailFast_ThenThrowsException() {
        assertThatThrownBy(() -> {
            failFast1();
        }).isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    public void whenFailFast_ThenThrowsExceptionInSecondIteration() {
        assertThatThrownBy(() -> {
            failFast2();
        }).isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    public void whenFailSafe_ThenDoesNotThrowException() {
        assertThat(failSafe1()).isGreaterThanOrEqualTo(0);
    }

}
