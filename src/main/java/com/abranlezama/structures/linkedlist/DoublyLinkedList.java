package com.abranlezama.structures.linkedlist;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    // internal node class to represent data
    private static class Node<T> {
        private T data;
        private Node<T> next, prev;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // empty this linked list, O(n)
    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = trav = null;
        size = 0;
    }

    // return the size linked list
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // add element to the tail of the linked list, (1)
    public void add(T elem) {
        addLast(elem);
    }

    // add element to the tail of the linked list, O(1)
    public void addLast(T elem) {
        if (isEmpty()) {
            head = tail = new Node<>(elem, null, null);
        } else {
            tail.next = new Node<>(elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    // add element to the beginning of the linked list, O(1)
    public void addFirst(T elem) {
        if (isEmpty()) {
            head = tail = new Node<>(elem, null, null);
        } else {
            head.prev = new Node<>(elem, null, head);
            head = head.prev;
        }
        size++;
    }

    // add an element at a specified index
    public void addAt(int index, T data) throws Exception {
        if (index < 0 || index >= size) throw new Exception("Illegal Index");

        if (index == 0) {
            addFirst(data);
            return;
        }

        if (index == size -1) {
            addLast(data);
            return;
        }

        Node<T> temp = head;
        for (int i = 0; i < index - 1; i++) temp = temp.next;

        Node<T> newNode = new Node<>(data, temp, temp.next);
        temp.next.prev = newNode;
        temp.next = newNode;
        size++;
    }

    // retrieve value of first node, if it exists, O(1)
    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return  head.data;
    }

    // retrieve value of last node, if it exists, O(1)
    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return tail.data;
    }

    // remove the first value at the head of the linked list, O(1)
    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("Empty List");

        T data = head.data;
        head = head.next;
        --size;

        //if list becomes empty, set tail to null
        if (isEmpty()) tail = null;
        else head.prev = null; // memory cleanup of previous node

        return data;
    }

    // remove value at tail of linked list, O(1)
    public T removeLast() {
        if (isEmpty()) throw new RuntimeException("Empty List");

        T data = tail.data;
        tail = tail.prev;
        --size;

        // if list becomes empty, set head to null
        if (isEmpty()) head = null;
        else tail.next = null; // memory cleanup of last head

        return data;
    }

    // remove an arbitrary node from linked lists, O(1)
    public T remove(Node<T> node) {
        if (node.prev == null) return removeFirst();
        if (node.next == null) return removeLast();

        // make pointers of adjacent nodes skip over 'node'
        node.next.prev = node.prev;
        node.prev.next = node.next;

        T data = node.data;

        // memory cleanup
        node.data = null;
        node = node.prev = node.next = null;

        --size;

        return data;
    }

    // remove node at a particular index, O(n)
    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException();

        int i;
        Node<T> trav;

        if (index < size / 2) {
            for (i = 0, trav = head; i!= index; i++) trav = trav.next;
        } else {
            for (i = size - 1, trav = tail; i != index; i--) trav = trav.prev;
        }

        return remove(trav);
    }

    // remove a particular value in the linked list, O(n)
    public boolean remove(Object obj) {
        Node<T> trav = head;

        // support search for null
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
        } else { // search for nonnull object
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    // find index of a particular value in linked list, O(n)
    public int indexOf(Object obj) {
        int index = 0;
        Node<T> trav = head;

        // support searching for null
        if (obj == null) {
            for (; trav != null; trav = trav.next, index++) {
                if (trav.data == null) return index;
            }
        } else { // search for nonnull objects
            for (; trav != null; trav = trav.next, index++) {
                if (obj.equals(trav.data)) return index;
            }
        }
        return -1;
    }

    // check if a value is present in the list
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> trav = head;
        while (trav != null) {
            sb.append(trav.data);
            if (trav.next != null) sb.append(", ");
            trav = trav.next;
        }
        sb.append(" ]");
        return sb.toString();
    }
}
