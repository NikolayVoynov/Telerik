package com.company.dsa.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
    private E[] items;
    private int head, tail, size;

    public ArrayQueue() {
        items = (E[]) new Object[10];
        size = 0;
        head = 0;
    }

    @Override
    public void enqueue(E element) {
        if (tail == items.length) {
            items = Arrays.copyOf(items, items.length * 2);
        }


        items[tail] = element;
        tail++;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E headDequeued = items[head];
        for (int i = 1; i < size - 1; i++) {
            items[i - 1] = items[i];
        }

        return headDequeued;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return items[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
