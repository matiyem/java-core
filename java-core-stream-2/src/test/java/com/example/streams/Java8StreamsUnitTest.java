package com.example.streams;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class Java8StreamsUnitTest {

    private List<String> list;

    @Before
    public void init() {
        list = new ArrayList<>();
        list.add("One");
        list.add("OneAndOnly");
        list.add("Derek");
        list.add("Change");
        list.add("factory");
        list.add("justBefore");
        list.add("Italy");
        list.add("Italy");
        list.add("Thursday");
        list.add("");
        list.add("");
    }

    @Test
    public void checkStreamCount_whenCreating_givenDifferentSources() {
        String[] arr = new String[] { "a", "b", "c" };
        Stream<String> streamArr = Arrays.stream(arr);
        assertEquals(streamArr.count(), 3);

        Stream<String> streamOf = Stream.of("a", "b", "c");
        assertEquals(streamOf.count(), 3);
        //باعث میشود که یک استریم یونیک ساخته شود
        long count = list.stream().distinct().count();
        assertEquals(count, 9);
    }

    @Test
    public void checkStreamCount_whenOperationFilter_thanCorrect() {
        Stream<String> streamFilter = list.stream().filter(element -> element.isEmpty());
        assertEquals(streamFilter.count(), 2);
    }

    @Test
    public void checkStreamCount_whenOperationMap_thanCorrect() {
        List<String> uris = new ArrayList<>();
        uris.add("C:\\My.txt");
        Stream<Path> streamMap = uris.stream().map(uri -> Paths.get(uri));
        assertEquals(streamMap.count(), 1);

        List<Detail> details = new ArrayList<>();
        details.add(new Detail());
        details.add(new Detail());
        //flatMap برای المنت های داخلی یک استریم بر میگرداند
        //در این مثال، ما لیستی از عناصر از نوع Detail را داریم. کلاس Detail حاوی یک فیلد PARTS است که یک List<String> است. با کمک متد flatMap() هر عنصر از فیلد PARTS استخراج و به جریان جدید حاصل اضافه می شود. پس از آن، جریان اولیه <Detail> از بین خواهد رفت.
        Stream<String> streamFlatMap = details.stream().flatMap(detail -> detail.getParts().stream());
        assertEquals(streamFlatMap.count(), 4);
    }

    @Test
    public void checkStreamCount_whenOperationMatch_thenCorrect() {
        //iterate میکند و هز المنتی که شامل h میباشد را بر میگرداند
        //به طور مشابه، متد anyMatch() همیشه false را برای جریان های خالی برمی گرداند:
        //باز هم، این معقول است، زیرا ما نمی‌توانیم عنصری را که این شرط را برآورده کند، پیدا کنیم.
        boolean isValid = list.stream().anyMatch(element -> element.contains("h"));
        //برای جریان‌های خالی، متد allMatch() با هر گزاره داده شده true برمی‌گرداند:
        //این یک پیش‌فرض معقول است، زیرا ما نمی‌توانیم هیچ عنصری را پیدا کنیم که گزاره را برآورده نکند.
        boolean isValidOne = list.stream().allMatch(element -> element.contains("h"));
        boolean isValidTwo = list.stream().noneMatch(element -> element.contains("h"));
        assertTrue(isValid);
        assertFalse(isValidOne);
        assertFalse(isValidTwo);
    }

    @Test
    public void checkStreamReducedValue_whenOperationReduce_thenCorrect() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(1);
        integers.add(1);
        Integer reduced = integers.stream().reduce(23, (a, b) -> a + b);
        assertTrue(reduced == 26);
    }

    @Test
    public void checkStreamContains_whenOperationCollect_thenCorrect() {
        List<String> resultList = list.stream().map(element -> element.toUpperCase()).collect(Collectors.toList());
        assertEquals(resultList.size(), list.size());
        assertTrue(resultList.contains(""));
    }

    @Test
    public void checkParallelStream_whenDoWork() {
        list.parallelStream().forEach(element -> doWork(element));
    }

    private void doWork(String string) {
        assertTrue(true); // just imitate an amount of work
    }
}
