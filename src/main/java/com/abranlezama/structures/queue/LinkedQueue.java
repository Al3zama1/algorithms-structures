package com.abranlezama.structures.queue;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedQueue<T> implements Iterable<T> {

    private LinkedList<T> list = new LinkedList<>();

    // initialize empty queue
    public LinkedQueue() {}

    // initialize queue with an element
    public LinkedQueue(T firstElem) {
        offer(firstElem);
    }

    // return the size of the queue
    public int size() {
        return list.size();
    }

    // returns whether or not the queue is empty
    public boolean isEmpty() {
        return  size() == 0;
    }

    // peek the element from the front of the queue
    // throws an error if the queue is empty
    public T peek() {
        if (isEmpty()) throw new RuntimeException("Empty Queue");
        return list.peekFirst();
    }

    // polls an element from the front of the queue
    // throws an error if the queue is empty
    public T poll() {
        if (isEmpty()) throw new RuntimeException("Empty Queue");
        return list.removeFirst();
    }

    // add an element to the back of the queue
    public void offer(T elem) {
        list.addLast(elem);
    }



    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
