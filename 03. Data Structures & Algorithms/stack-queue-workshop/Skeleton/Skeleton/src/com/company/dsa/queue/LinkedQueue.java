package com.company.dsa.queue;

import com.company.dsa.Node;

import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
    private Node<E> head, tail;
    private int size;

    public LinkedQueue() {
        size = 0;
    }

    @Override
    public void enqueue(E element) {
        Node node = new Node();
        node.data = element;
        if (tail != null) {
            tail.next = node;
        }

        tail = node;

        if (head == null) {
            head = node;
        }

        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E dataDequeue = head.data;
        head = head.next;
        size--;

        if (isEmpty()){
            tail = null;
        }

        return dataDequeue;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return head.data;
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
