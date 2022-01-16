package com.example.collections.comparation;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class HashMapUnitTest {
//    ممکن است کسی بپرسد، چرا به سادگی از یک لیست استفاده نمی کنیم و همه با هم از شر کلیدها خلاص نمی شویم؟ به خصوص که هش مپ برای ذخیره کلیدها حافظه بیشتری مصرف می کند و ورودی های آن مرتب نیستند. پاسخ در مزایای عملکرد برای جستجوی عناصر نهفته است.
//
//HashMap در بررسی وجود یک کلید یا بازیابی یک مقدار بر اساس یک کلید بسیار کارآمد است.
//    HashMap همراه با ArrayList یکی از پرکاربردترین ساختارهای داده در جاوا است. برخلاف پیاده سازی های مختلف لیست، HashMap از نمایه سازی برای انجام یک پرش به یک مقدار خاص استفاده می کند و زمان جستجو را حتی برای مجموعه های بزرگ ثابت می کند.
//
//استفاده از HashMap تنها زمانی معنا دارد که کلیدهای منحصر به فرد برای داده هایی که می خواهیم ذخیره کنیم در دسترس باشد. هنگام جستجوی موارد بر اساس کلید باید از آن استفاده کنیم و زمان دسترسی سریع یک نیاز مهم است.
//
//هنگامی که حفظ نظم یکسان آیتم ها در یک مجموعه مهم است، باید از HashMap استفاده نکنید.

    @Test
    void givenHashMap_whenItemAddedByKey_thenItCanBeRetrieved() {
        Map<String, String> map = new HashMap<>();
        map.put("123456", "Daniel");
        map.put("654321", "Marko");
        assertThat(map.get("654321")).isEqualTo("Marko");
    }

    @Test
    void givenHashMap_whenItemRemovedByKey_thenMapSizeIsReduced() {
        Map<String, String> map = new HashMap<>();
        map.put("123456", "Daniel");
        map.put("654321", "Marko");
        map.remove("654321");
        assertThat(map).hasSize(1);
    }

}
