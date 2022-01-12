package com.example.combiningCollections;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.exec.util.MapUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Create by Atiye Mousavi
 * Date: 1/10/2022
 * Time: 11:43 AM
 **/
public class CombiningMaps {

    public static Map<String, String> usingPlainJava(Map<String, String> first, Map<String, String> second) {
        Map<String, String> combined = new HashMap<>();
        combined.putAll(first);
        combined.putAll(second);
        return combined;
    }

    public static Map<String, String> usingJava8ForEach(Map<String, String> first, Map<String, String> second) {
        second.forEach((key, value) -> first.merge(key, value, String::concat));
        return first;
    }

    public static Map<String, String> usingJava8FlatMaps(Map<String, String> first, Map<String, String> second) {
        Map<String, String> combined = Stream.of(first, second).map(Map::entrySet).flatMap(Collection::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, String::concat));
        return combined;
    }

    public static Map<String, String> usingApacheCommons(Map<String, String> first, Map<String, String> second) {
        Map<String, String> combined = MapUtils.merge(first, second);
        return combined;
    }

    public static Map<String, String> usingGuava(Map<String, String> first, Map<String, String> second) {
        Map<String, String> combined = ImmutableMap.<String, String>builder()
                .putAll(first)
                .putAll(second)
                .build();
        return combined;
    }
}
