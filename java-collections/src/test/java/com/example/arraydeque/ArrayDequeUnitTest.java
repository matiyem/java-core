package com.example.arraydeque;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ArrayDequeUnitTest {

    @Test
    public void whenOffer_addsAtLast() {
        final Deque<String> deque = new ArrayDeque<>();
        //یک عنصر جدید را در صف قرار می دهد
        deque.offer("first");
        deque.offer("second");

        assertEquals("second", deque.getLast());
    }

    @Test
    public void whenPoll_removesFirst() {
        //اگر یک queue خالی باشد، متد poll مقدار null را برمی‌گرداند.
        final Deque<String> deque = new ArrayDeque<>();

        deque.offer("first");
        deque.offer("second");

//یک عنصر را از جلوی صف حذف می کندpoll
        assertEquals("first", deque.poll());
    }

    @Test
    public void whenPush_addsAtFirst() {
        final Deque<String> deque = new ArrayDeque<>();

        deque.push("first");
        deque.push("second");

        assertEquals("second", deque.getFirst());
    }

    @Test
    public void whenPop_removesLast() {
//        متد pop، NoSuchElementException را زمانی که stack خالی است، پرتاب می کند.
        final Deque<String> deque = new ArrayDeque<>();

        deque.push("first");
        deque.push("second");

        assertEquals("second", deque.pop());
    }
}
