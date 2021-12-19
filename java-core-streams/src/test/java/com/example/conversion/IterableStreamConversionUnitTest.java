package com.example.conversion;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class IterableStreamConversionUnitTest {

    @Test
    public void givenIterable_whenConvertedToStream_thenNotNull() {
        Iterable<String> iterable = Arrays.asList("Testing", "Iterable", "conversion", "to", "Stream");
//        رابط Iterable با در نظر گرفتن کلیات طراحی شده است و هیچ متد stream() را به تنهایی ارائه نمی دهد.
//
//به زبان ساده، می‌توانید آن را به متد ()StreamSupport.stream ارسال کنید و از نمونه Iterable داده شده یک Stream دریافت کنید.
//توجه داشته باشید که پارامتر دوم در StreamSupport.stream() تعیین می کند که جریان حاصل باید parallel یا sequential باشد.
//        همچنین، یک نکته جانبی سریع - استریم ها قابل استفاده مجدد نیستند، در حالی که Iterable قابل استفاده است. همچنین یک متد spliterator() را ارائه می دهد که یک نمونه java.lang.Spliterator را بر روی عناصر توصیف شده توسط Iterable داده شده برمی گرداند.
        //این یک عملیات برای تبدیل یک iterable به استریم است
        Assert.assertNotNull(StreamSupport.stream(iterable.spliterator(), false));
    }

    @Test
    public void whenConvertedToList_thenCorrect() {
        Iterable<String> iterable = Arrays.asList("Testing", "Iterable", "conversion", "to", "Stream");

        List<String> result = StreamSupport.stream(iterable.spliterator(), false).map(String::toUpperCase).collect(Collectors.toList());

        assertThat(result, contains("TESTING", "ITERABLE", "CONVERSION", "TO", "STREAM"));
    }
}
