package com.abranlezama.structures.queue;

import java.util.Iterator;
import java.util.LinkedList;

public class QueueImp<T> implements Iterable<T> {

    private LinkedList<T> list = new LinkedList<>();

    // initialize empty queue
    public QueueImp() {}

    // initialize queue with an element
    public QueueImp(T element) {
        offer(element);
    }

    // return size of the queue
    public int size() {
        return list.size();
    }

    // check if queue is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // retrieve element at the front of the queue
    public T peek() {
        if (list.isEmpty()) throw new RuntimeException("Queue is empty");
        return list.peekFirst();
    }

    // remove element from the front of the queue
    public T poll() {
        if (list.isEmpty()) throw new RuntimeException("Empty queue");
        return list.removeFirst();
    }

    // add element to the front of the queue
    public void offer(T element) {
        list.addLast(element);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
