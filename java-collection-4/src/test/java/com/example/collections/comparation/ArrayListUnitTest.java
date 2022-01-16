package com.example.collections.comparation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListUnitTest {
//    استفاده از ArrayList زمانی منطقی به نظر می رسد که حفظ ترتیب یکسان آیتم ها مهم باشد و زمان دسترسی سریع بر اساس موقعیت/شاخص یک معیار مهم است.
//
//زمانی که ترتیب اقلام مهم نیست از ArrayList استفاده نکنید. همچنین، زمانی که موارد نیاز به اضافه شدن به یک موقعیت خاص دارند، سعی کنید از آن اجتناب کنید. به همین ترتیب، به خاطر داشته باشید که ArrayList ممکن است بهترین گزینه نباشد زمانی که جستجو برای مقادیر خاص مورد نیاز است، به خصوص اگر لیست بزرگ باشد.

    @Test
    void givenArrayList_whenItemAddedToSpecificIndex_thenItCanBeRetrieved() {
        List<String> list = new ArrayList<>();
        list.add("Daniel");
        list.add(0, "Marko");
        assertThat(list).hasSize(2);
        assertThat(list.get(0)).isEqualTo("Marko");
    }

    @Test
    void givenArrayList_whenItemRemovedViaIndex_thenListSizeIsReduced() {
        List<String> list = new ArrayList<>(Arrays.asList("Daniel", "Marko"));
        list.remove(1);
        assertThat(list).hasSize(1);
        assertThat(list).doesNotContain("Marko");
    }

}
