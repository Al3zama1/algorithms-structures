package com.abranlezama.structures.stack;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class StackImp<T> implements Iterable<T> {

    // stack implemented using the doubly linked list provided by java
    private LinkedList<T> list = new LinkedList<>();

    // create an empty stack
    public StackImp() {}

    // create a stack with an initial element
    public StackImp(T element) {
        push(element);
    }

    // return the number of elements in the stack
    public int size() {
        return list.size();
    }

    // check if the stack is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // add element to the stack
    public void push(T element) {
        list.addLast(element);
    }

    // remove element from the stack
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        return list.removeLast();
    }

    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return list.peekLast();
    }


    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
