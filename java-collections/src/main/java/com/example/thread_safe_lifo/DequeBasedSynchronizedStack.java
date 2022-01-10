package com.example.thread_safe_lifo;

import java.util.ArrayDeque;

/**
 * Create by Atiye Mousavi
 * Date: 1/8/2022
 * Time: 12:08 PM
 **/
public class DequeBasedSynchronizedStack<T> {

    private ArrayDeque<T> dequeStore = new ArrayDeque<>();

    public DequeBasedSynchronizedStack(int initialCapacity) {
        this.dequeStore = new ArrayDeque<>(initialCapacity);
    }

    public DequeBasedSynchronizedStack() {
    }

    public synchronized T pop() {
        return this.dequeStore.pop();
    }

    public synchronized void push(T element) {
         this.dequeStore.push(element);
    }
    public synchronized T peek(){
        return this.dequeStore.peek();
    }
    public synchronized int size(){
        return this.dequeStore.size();
    }
}
