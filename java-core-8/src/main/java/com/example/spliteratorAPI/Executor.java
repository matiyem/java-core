package com.example.spliteratorAPI;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Create by Atiye Mousavi
 * Date: 12/13/2021
 * Time: 2:23 PM
 **/
public class Executor {

    public static int countAutors(Stream<Author> stream){
        RelatedAuthorCounter wordCounter=stream.reduce(new RelatedAuthorCounter(0,true),
               RelatedAuthorCounter::accumlate,RelatedAuthorCounter::combine );
        return wordCounter.getCounter();
    }
    public static List<Article> generateElements(){
        return Stream.generate(()->new Article("java")).limit(35000).collect(Collectors.toList());
    }
}
