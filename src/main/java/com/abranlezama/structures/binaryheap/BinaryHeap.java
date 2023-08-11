package com.abranlezama.structures.binaryheap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    // dynamic list to track elements inside the heap
    private List<T> heap = null;

    // construct an initially empty priority queue
    public BinaryHeap() {
        this(1);
    }

    // construct a priority queue with an initial capacity
    public BinaryHeap(int size) {
        heap = new ArrayList<>(size);
    }

    /*
    construct a priority queue using heapify in O(n)
    http://www.cs.umd.edu/~meesh/351/mount/lectures/lect14-heapsort-analysis-part.pdf
     */
    public BinaryHeap(T[] elements) {
        int heapSize = elements.length;
        heap = new ArrayList<>(heapSize);

        // place all element in the heap
        for (int i = 0; i < heapSize; i++) heap.add(elements[i]);

        // heapify process, O(n)
        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) sink(i);
    }

    // priority queue construction, O(n)
    public BinaryHeap(Collection<T> elements) {
        int heapSize = elements.size();
        heap = new ArrayList<>(heapSize);

        // add all elements of the given collection to the heap
        heap.addAll(elements);

        // heapify process, O(n)
        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) sink(i);
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

//     returns value of element with the highest priority.
    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }

    // removes root of the heap, O(log(n))
    public T poll() {
        return removeAt(0);
    }

    // test if element is in the heap, O(n)
    public boolean contains(T element) {
        // linear scan to check containment
        for (int i = 0; i < size(); i++) {
            if (heap.get(i).equals(element)) return true;
        }
        return false;
    }

    // add element to the priority queue, element must not be null, O(log(n))
    public void add(T element) {
        if (element == null) throw new IllegalArgumentException();

        heap.add(element);

        int indexOfElement = size() - 1;
        swim(indexOfElement);
    }

    // tests if the value of node i <= node j. assumes i & j are valid indices, O(1)
    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    // perform bottom up node swim, O(log(n))
    private void swim(int k) {
        // grab index of the next parent node WRT to k
        int parent = (k - 1) / 2;

        // keep swimming while we have not reached the root and we are less than our parent
        while (k > 0 && less(k, parent)) {
            swap(parent, k);
            k = parent;
            // grab index of the next parent node WRT to k
            parent = (k - 1) / 2;
        }
    }

    // perform top down node sink, O(log(n))
    private void sink(int k) {
        int heapSize = size();

        while (true) {
            int left = 2 * k + 1; // left child
            int right = 2 * k + 2; // right child
            int smallest = left; // assume left is the smallest node of the two children

            // find which is smaller left or right
            // if right is smaller, set smaller to be right
            if (right < heapSize && less(right, left)) smallest = right;
            if (left >= heapSize || less(k, smallest)) break;

            // move down the tree following the smallest node
            swap(smallest, k);
            k = smallest;
        }
    }

    private void swap(int i, int j) {
        T iElement = heap.get(i);
        T jElement = heap.get(j);

        heap.set(i, jElement);
        heap.set(j, iElement);
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

    // remove node at a particular index, O(log(n))
    private T removeAt(int i) {
        if (isEmpty()) return null;

        int indexOfLastElement = size() - 1;
        T removedData = heap.get(i);
        swap(i, indexOfLastElement);

        // obliterate the value
        heap.remove(indexOfLastElement);

        // check if the last element was removed
        if (i == indexOfLastElement) return removedData;
        T element = heap.get(i);

        // try sinking
        sink(i);

        // if sinking did not work, try swimming
        if (heap.get(i).equals(element)) swim(i);;
        return removedData;
    }

    public boolean isMinHeap(int k) {
        int heapSize = size();
        if (k >= heapSize) return true;

        int left = 2 * k + 1;
        int right = 2 * k + 2;

        /*
        make sure the current node k is less than both of its children, left and right, if they exist
        return false otherwise
         */
        if (left < heapSize && !less(k, left)) return false;
        if (right < heapSize && !less(k, right)) return false;

        // recurse on both children to make sure they are also valid heaps
        return isMinHeap(left) && isMinHeap(right);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
