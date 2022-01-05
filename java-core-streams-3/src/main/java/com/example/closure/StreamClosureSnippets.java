package com.example.closure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * created by Atiye Mousavi
 * Date: 12/31/2021
 * Time: 6:35 PM
 **/


public class StreamClosureSnippets {

    public static void main(String[] args) throws IOException {

        Arrays.asList("Red","Blue","Green")
                .stream()
                .filter(c ->c.length()>4)
                .map(String::toUpperCase)
                .forEach(System.out::print);

        String[] colors={"Red","Blue","Green"};
        Arrays.stream(colors).map(String::toUpperCase)
                .forEach(System.out::println);
        try (Stream<String> lines= Files.lines(Paths.get("/path/tp/file"))){

        }
    }
}
