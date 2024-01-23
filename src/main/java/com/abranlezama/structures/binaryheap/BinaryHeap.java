package com.abranlezama.structures.binaryheap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// min priority queue implementation

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap = null;

    public BinaryHeap() {
        this(1);
    }

    public BinaryHeap(int size) {
        heap = new ArrayList<>(size);
    }

    /*
    construct priority queue using heapify in O(n) time.
    Can only be done when we have all the elements from beginning
     */
    public BinaryHeap(T[] elems) {
        int heapSize = elems.length;
        heap = new ArrayList<>(heapSize);

        // place elements in heap
        for (int i = 0; i < heapSize; i++) heap.add(elems[i]);

        // heapify process, O(n)
        for (int i = heapSize / 2 - 1; i >= 0; i--) sink(i);
    }

    // O(n)
    public BinaryHeap(Collection<T> elems) {
        int heapSize = elems.size();
        heap = new ArrayList<>(heapSize);

        heap.addAll(elems);

        // heapify process, O(n)
        for (int i = heapSize / 2 - 1; i >= 0; i--) sink(i);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // clears everything inside the heap, O(n)
    public void clear() {
        heap.clear();
    }

    // return the size of the heap
    public int size() {
        return heap.size();
    }

    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }

    // removes the root of the heap, O(log(n))
    public T poll() {
        return removeAt(0);
    }

    // O(n)
    public boolean contains(T elem) {
        for (int i = 0; i < size(); i++) {
            if (heap.get(i).equals(elem)) return true;
        }
        return false;
    }

    // add element to the priority queue, the element must not be null, O(log(n))
    public void add(T elem) {
        if (elem == null) throw new IllegalArgumentException();

        heap.add(elem);

        int indexOfLastElem = size() - 1;
        swim(indexOfLastElem);
    }

    // perform bottom up node swim, O(log(n))
    private void swim(int k) {
        int parent = (k - 1) / 2;

        while (k > 0 && less(k, parent)) {
            k = parent;
            parent = (k - 1) / 2;
        }
    }

    // top down node sink, O(log(n))
    private void sink(int k) {
        int heapSize = size();
        while (true) {
            int left = k * 2 + 1;
            int right = k * 2 + 2;
            int smallest = left; // assume left child is the smallest node of th two children

            if (right < heapSize && less(right, left)) smallest = right;

            // stop if we're outside the bounds of the tree
            // or stop early if we cannot sink k anymore
            if (left >= heapSize || less(k, smallest)) break;

            swap(smallest, k);
            k = smallest;
        }
    }

    // remove a particular element in the heap, O(n)
    public boolean remove(T element) {
        if (element == null) return false;
        // linear removal via search, O(n)
        for (int i = 0; i < size(); i++) {
            if (element.equals(heap.get(i))) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    // remove a node at a particular index, O(log(n))
    private T removeAt(int i) {
        if (isEmpty()) return null;

        int indexOfLastElem = size() - 1;
        T removedData = heap.get(i);
        swap(i, indexOfLastElem);

        // obliterate the value
        heap.remove(indexOfLastElem);

        // check if the last element was removed
        if (i == indexOfLastElem) return removedData;
        T elem = heap.get(i);

        // try sinking
        sink(i);

        // if sinking didn't work, try swimming
        if (heap.get(i).equals(elem)) swim(i);
        return removedData;
    }

    // check if this heap is a min heap
    public boolean isMinHeap(int k) {
        int heapSize = size();
        if (k >= heapSize) return true;

        int left = 2 * k + 1;
        int right = 2 * k + 2;

        if (left < heapSize && !less(k, left)) return false;
        if (right < heapSize && !less(k, right)) return false;

        // recurse on both children to make sure they're also valid heaps
        return isMinHeap(left) && isMinHeap(right);
    }

    private void swap(int i, int j) {
        T elemI = heap.get(i);
        T elemJ = heap.get(j);

        heap.set(i, elemJ);
        heap.set(j, elemI);
    }

    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
