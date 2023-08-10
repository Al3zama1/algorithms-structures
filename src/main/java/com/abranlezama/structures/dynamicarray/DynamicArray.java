package com.abranlezama.structures.dynamicarray;

import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T> {

    private T[] arr;
    private int len = 0; // elements in array
    private int capacity; // actual array capacity

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        if (index < 0 || index >= len) throw new IndexOutOfBoundsException("Index Out Of Bounds");
        return arr[index];
    }

    public void set(int index, T data) {
        if (index < 0 || index >= len) throw new IndexOutOfBoundsException("Index Out Of Bounds");
        arr[index] = data;
    }

    public void clear() {
        for (int i = 0; i < len; i++) arr[i] = null;
        len = 0;
    }

    public void add(T data) {
        if (len + 1 >= capacity){
            if (capacity == 0) capacity = 1;
            else capacity *= 2;

            T[] newArr = (T[]) new Object[capacity];
            System.arraycopy(arr, 0, newArr, 0, len);

            arr = newArr;
        }
        arr[len++] = data;
    }

    public T removeAt(int rmIndex) {
        if (rmIndex < 0 || rmIndex >= len) throw new IndexOutOfBoundsException("Index Out Of Bounds");
        T data = arr[rmIndex];
        T[] newArr = (T[]) new Object[len - 1];
        for (int i = 0, j = 0; i < len; i++, j++) {
            if (i == rmIndex) j--;
            else newArr[j] = arr[i];
        }

        arr = newArr;
        capacity = --len;
        return data;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(obj)) return i;
        }
        return - 1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }
}
