package com.example.combiningCollections;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Create by Atiye Mousavi
 * Date: 1/10/2022
 * Time: 11:11 AM
 **/
public class CombiningLists {

    public static List<Object> usingNativeJava(List<Object> first, List<Object> second) {
//        البته ساده ترین راه حل برای ترکیب مجموعه ها استفاده از متد addAll() است، مانند مثال لیست زیر، اما شایان ذکر است که این متد یک مجموعه جدید با ارجاعات اضافی به همان اشیایی که در دو مجموعه اول هستند ایجاد می کند:
        List<Object> combined = new ArrayList<>();
        combined.addAll(first);
        combined.addAll(second);
        return combined;
    }

    public static List<Object> usingJava8ObjectStream(List<Object> first, List<Object> second) {
        List<Object> combined = Stream.concat(first.stream(), second.stream()).collect(Collectors.toList());
        return combined;
    }

    public static List<Object> usingJava8FlatMaps(List<Object> first, List<Object> second) {
        List<Object> combined = Stream.of(first, second).flatMap(Collection::stream).collect(Collectors.toList());
        return combined;

    }

    public static List<Object> usingApacheCommons(List<Object> first, List<Object> second) {
        List<Object> combined = ListUtils.union(first, second);
        return combined;
    }

    public static List<Object> usingGuava(List<Object> first, List<Object> second) {
        Iterable<Object> combineIterables = Iterables.unmodifiableIterable(
                Iterables.concat(first, second));
        List<Object> combined = Lists.newArrayList(combineIterables);
        return combined;
    }
}
