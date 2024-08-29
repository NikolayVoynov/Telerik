package com.company.dsa;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
    private Node head;
    private Node tail;
    private int size = 0;

    public LinkedList() {
    }

    public LinkedList(Iterable<T> iterable) {
        iterable.forEach(this::addLast);
    }

    @Override
    public void addFirst(T value) {
        Node newNode = new Node(value);

        if (head != null) {
            newNode.next = head;
            head.prev = newNode;
        }

        head = newNode;

        if (tail == null) {
            tail = newNode;
        }

        size++;
    }

    @Override
    public void addLast(T value) {
        Node newNode = new Node(value);

        if (head == null && tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }

        tail = newNode;

        size++;
    }

    @Override
    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new NoSuchElementException();
        }

        if (index == size) {
            addLast(value);
            return;
        }

        if (index == 0) {
            addFirst(value);
            return;
        }

        Node newNode = new Node(value);
        Node currentNode = head;
        Node nextOfCurrent = head.next;

        for (int i = 1; i < index; i++) {
            currentNode = currentNode.next;
            nextOfCurrent = currentNode.next;
        }

        currentNode.next = newNode;
        newNode.prev = currentNode;
        newNode.next = nextOfCurrent;
        nextOfCurrent.prev = newNode;

        size++;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.value;
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.value;
    }

    @Override
    public T get(int index) {
        if (isEmpty() || index < 0 || index > size) {
            throw new NoSuchElementException();
        }

        if (index == 0) {
            return head.value;
        }

        if (index == size) {
            return tail.value;
        }

        Node currentNode = head;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode.value;
    }

    @Override
    public int indexOf(T value) {
        if (isEmpty()) {
            return -1;
        }

        Node currentNode = head;
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (currentNode.value == value) {
                index = i;
                break;
            }
            currentNode = currentNode.next;
        }

        return index;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T removed = head.value;
        head = head.next;
        size--;

        return removed;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T removed = tail.value;
        tail = tail.prev;
        size--;
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node currentElem = head;

            @Override
            public boolean hasNext() {
                return currentElem != null;
            }

            @Override
            public T next() {
                T value = currentElem.value;
                currentElem = currentElem.next;
                return value;
            }
        };
    }

    private class Node {
        T value;
        Node prev;
        Node next;

        Node(T value) {
            this.value = value;
        }
    }

    private boolean isEmpty() {
        return size == 0;
    }
}
