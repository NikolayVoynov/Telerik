package com.company.oop;

import java.util.Arrays;
import java.util.Iterator;

public class MyListImpl<T> implements MyList<T> {

    private static final int DEFAULT_CAPACITY = 4;

    private T[] elements;
    private int size;

    public MyListImpl() {
        this(DEFAULT_CAPACITY);
    }

    public MyListImpl(int initialCapacity) {
        this.elements = (T[]) new Object[initialCapacity];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return elements.length;
    }

    @Override
    public void add(T element) {
        if (size == this.capacity()) {
            elements = Arrays.copyOf(elements, this.capacity() * 2);
        }
        elements[size] = element;
        size++;
    }

    @Override
    public T get(int index) {
        return elements[index];
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i <= size; i++) {
            if (element.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        for (int i = size; i >= 0; i--) {
            if (element.equals(elements[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i <= size; i++) {
            if (element.equals(elements[i])) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void removeAt(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }

        size--;
        T[] newElements = (T[]) new Object[size];

        for (int i = 0; i < index; i++) {
            newElements[i] = elements[i];
        }

        for (int i = index; i < size; i++) {
            newElements[i] = elements[i + 1];
        }

        elements = Arrays.copyOf(newElements, this.capacity() - 1);
    }

    @Override
    public boolean remove(T element) {
        int indexFound = -1;
        T[] newElements = (T[]) new Object[size - 1];
        for (int i = 0; i <= size; i++) {
            if (element.equals(elements[i])) {
                indexFound = i;
                size--;
                break;
            }
        }

        if (indexFound != -1) {
            for (int i = 0; i < indexFound; i++) {
                newElements[i] = elements[i];
            }

            for (int i = indexFound; i < size; i++) {
                newElements[i] = elements[i + 1];
            }

            elements = Arrays.copyOf(newElements, this.capacity() - 1);

            return true;
        }

        return false;
    }

    @Override
    public void clear() {
        elements = (T[]) new Object[capacity()];
        size = 0;
    }

    @Override
    public void swap(int from, int to) {
        T temp = elements[to];
        elements[to] = elements[from];
        elements[from] = temp;
    }

    @Override
    public void print() {
        T[] newArr = Arrays.copyOf(elements, size);

        System.out.println(Arrays.toString(newArr));
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {

        private int currentIndex;

        MyListIterator() {
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            T currentElement = elements[currentIndex];
            currentIndex++;
            return currentElement;
        }
    }
}
