package com.abranlezama.structures.dynamicarray;

import java.util.Arrays;
import java.util.Iterator;

public class DynamicArray implements Iterable<Integer>{

    private static final int DEFAULT_CAP = 8;

    public int[] arr;
    public int len = 0;
    private int capacity = 0;

    // initialize array with default capacity
    public DynamicArray() {
        this(DEFAULT_CAP);
    }

    // initialize array with a certain capacity
    public DynamicArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = new int[capacity];
    }

    // given an array make it a dynamic array
    public DynamicArray(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("Array cannot be null");
        this.arr = Arrays.copyOf(arr, arr.length);
        capacity = len = arr.length;
    }

    // return the size of the array
    public int size() {
        return len;
    }

    // return true/false on whether the array is empty
    public boolean isEmpty() {
        return len == 0;
    }

    public int get(int index) {
        return arr[index];
    }

    public void set(int index, int elem) {
        if (index >= len) throw new IndexOutOfBoundsException("Index Out of Bounds");
        arr[index] = elem;
    }

    // add an element to the dynamic array
    public void add(int elem) {
        if (len + 1 >= capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2; // double the size
            arr = Arrays.copyOf(arr, capacity); // pads with extra 0/null elements
        }
        arr[len++] = elem;
    }

    /*
    Removes the element at the specified index in the list.
    If possible, avoid calling this method as it takes O(n) time
    to remove an element since you have to reconstruct the array
     */
    public void removeAt(int rmIndex) {
        System.arraycopy(arr, rmIndex + 1, arr, rmIndex, len - rmIndex - 1);
        --len;
        --capacity;
    }

    /*
    Search and remove an element if it is found in the array.
    If possible, avoid calling this method as it takes O(n) time
     */
    public boolean remove(int elem) {
        for (int i = 0; i < len; i++) {
            if (arr[i] == elem) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < len;
            }

            @Override
            public Integer next() {
                return arr[index++];
            }
        };
    }

    @Override
    public String toString() {
        if (len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; i++) sb.append(arr[i] + ", ");
            return sb.append(arr[len - 1] + "]").toString();
        }
    }
}
