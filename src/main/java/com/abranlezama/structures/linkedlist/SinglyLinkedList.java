package com.abranlezama.structures.linkedlist;


import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    // Internal node class to represent data
    private static class Node<T> {
        private T data;
        private Node<T> prev, next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // empty linked list, O(n)
    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = null;
        size = 0;
    }

    // return size of linked list, O(1)
    public int size() {
        return size;
    }

    // check if the linked list is empty, O(1)
    public boolean isEmpty() {
        return size() == 0;
    }

    // add element to the tail of the linked list, O(n)
    public void add(T element) {
        addLast(element);
    }

    // add a node to the tail of the linked list, O(1)
    public void addLast(T element) {
        if (isEmpty()) {
            head = tail = new Node<>(element, null);
        } else {
            tail.next = new Node<>(element, null);
            tail = tail.next;
        }
        size++;
    }

    // add a node to the head of the linked list, O(1)
    public void addFirst(T element) {
        if (isEmpty()) {
            head = tail = new Node<>(element, null);
        } else {
            head = new Node<>(element, head);
        }
        size++;
    }

    // add element at a specified index, O(n)
    public void addAt(int index, T element) {
        if (index < 0 || index > size) throw new IllegalArgumentException("Illegal Index");
        if (index == 0) addFirst(element);
        if (index == size) addLast(element);

        Node<T> trav = head;
        for (int i = 0; i < index - 1; i++) trav = trav.next;

        trav.next = new Node<>(element, trav.next);
        size++;
    }

    // retrieve first value
    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return head.data;
    }

    // retrieve last value
    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return tail.data;
    }

    // remove value at head of the list, O(1)
    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        Node<T> oldHead = head;
        head = head.next;
        oldHead.next = null;
        --size;

        if (isEmpty()) tail = null;
        return oldHead.data;
    }

    // remove tail of list, O(n)
    public T removeLast() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        if (size == 1) return removeFirst();
        Node<T> trav = head;

        while (trav.next != tail) trav = trav.next;
        T data = trav.next.data;
        trav.next = null;
        tail = trav;
        size--;

        return data;
    }

    // remove arbitrary node from linked list, O(n)
    private T remove(Node<T> node) {
        if (node == head) return removeFirst();
        if (node == tail) return removeLast();

        Node<T> trav = head;

        while (trav.next != node) trav = trav.next;
        T data = trav.next.data;
        trav.next = node.next;
        node.next = null;
        node.data = null;

        return data;
    }

    // remove a node at a particular index, O(n)
    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException();
        if (index == 0) return removeFirst();
        if (index == size -1) return removeLast();

        Node<T> trav = head;
        for (int i = 0; i < index; i++) trav = trav.next;
        Node<T> node = trav.next;
        trav.next = node.next;
        node.next = null;

        return node.data;
    }

    // remove a particular value in the linked list, O(n)
    public boolean remove(Object obj) {
        Node<T> trav = head;

        // support searching for null
        if (obj == null) {
            for ( trav = head; trav != null; trav = trav.next) {
                remove(trav);
                return true;
            }
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    // find the index of a particular value in the lined list, O(n)
    public int indexOf(Object obj) {
        int index = 0;
        Node<T> trav = head;

        // support searching for null
        if (obj == null) {
            for (; trav != null; trav = trav.next, index++) {
                if (trav.data == null) return index;
            }
        } else {
            for (; trav != null; trav = trav.next, index++) {
                if (obj.equals(trav.data)) return index;
            }
        }
        return -1;
    }

    // check a value is contained within the list
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> trav = head;
        while (trav !=  null) {
            sb.append(trav.data);
            if (trav.next != null) sb.append(", ");
            trav = trav.next;
        }
        sb.append(" ]");
        return sb.toString();
    };

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav.next != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

}
