package com.abranlezama.structures.stack;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class ListStack<T> implements Iterable<T> {

    private LinkedList<T> list = new LinkedList<>();

    // create an empty stack
    public ListStack() {}

    // create a stack with an initial element
    public ListStack(T firstElem) {
        push(firstElem);
    }

    // return the number of element in the stack
    public int size() {
        return list.size();
    }

    // check if the stack is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // push an element on the stack
    public void push(T elem) {
        list.addLast(elem);
    }

    // pop an element off the stack, throws an error if the stack is empty
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        return list.removeLast();
    }

    // peek the top of the stack without removing an element.
    // throws an exception if the stack is empty
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return list.peekLast();
    }

    // search of an element starting from the top of the stack
    // returns -1 if the element is not present in the stack
    public int search(T elem) {
        return list.lastIndexOf(elem);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
