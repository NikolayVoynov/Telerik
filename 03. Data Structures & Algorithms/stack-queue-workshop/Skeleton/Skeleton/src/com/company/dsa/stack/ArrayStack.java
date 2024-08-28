package com.company.dsa.stack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E> {
    private E[] items;
    private int top;

    public ArrayStack() {
        this.items = (E[]) new Object[10];
        this.top = 0;
    }

    @Override
    public void push(E element) {
        if (top == items.length) {
            items = Arrays.copyOf(items, items.length * 2);
        }

        items[top] = element;
        top++;
    }

    @Override
    public E pop() {
        if (!isEmpty()) {
            top--;
            return items[top];
        }

        throw new NoSuchElementException();
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[top-1];
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

}
