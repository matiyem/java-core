package com.example.spliteratorAPI;

import java.util.Spliterator;
import java.util.concurrent.Callable;

/**
 * Create by Atiye Mousavi
 * Date: 12/13/2021
 * Time: 3:12 PM
 **/
public class Task implements Callable<String> {
    private Spliterator<Article> spliterator;
    private final static String SUFFIX="- published by Bealdung";
    public Task(Spliterator<Article> spliterator) {
        this.spliterator = spliterator;
    }

    @Override
    public String call() {
        int current = 0;
        while (spliterator.tryAdvance(article -> {
            article.setName(article.getName()
                    .concat(SUFFIX));
        })) {
            current++;
        }
        ;
        return Thread.currentThread()
                .getName() + ":" + current;
    }
}
