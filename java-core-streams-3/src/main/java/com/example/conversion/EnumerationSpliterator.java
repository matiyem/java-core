package com.example.conversion;

import java.util.Enumeration;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * Create by Atiye Mousavi
 * Date: 1/3/2022
 * Time: 2:48 PM
 **/
public class EnumerationSpliterator<T> extends Spliterators.AbstractSpliterator<T> {
    private final Enumeration<T> enumeration;

    public EnumerationSpliterator(long est,int additionalCharacteristics,Enumeration<T> enumeration){
        super(est,additionalCharacteristics);
        this.enumeration=enumeration;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        if (enumeration.hasMoreElements()){
            action.accept(enumeration.nextElement());
            return true;
        }
        return false;
    }

    @Override
    public void forEachRemaining(Consumer<? super T> action) {
        while(enumeration.hasMoreElements())
            action.accept(enumeration.nextElement());
    }
}
