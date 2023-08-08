package com.abranlezama.algorithms;

/*
Heap Sort Complexity

Time Complexity
Best	O(nlog n)
Worst	O(nlog n)
Average	O(nlog n)
Space Complexity	O(1)
Stability	No
 */

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2, 10, 8, 7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        int n = arr.length;
        /*
        build a max heap
        n / 2 - 1 gives index of last non-leaf node
         */
        for (int i = (n / 2) - 1; i >= 0; i--) heapify(arr, n, i);

        // heap sort
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        // find the largest element among root, left, and right child
        int largest = i;
        int leftChild = i * 2 + 1;
        int rightChild = i * 2 + 2;

        if (leftChild < n && arr[leftChild] > arr[largest]) largest = leftChild;
        if (rightChild < n && arr[rightChild] > arr[largest]) largest = rightChild;

        // swap and continue heapifying if root is not largest
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }
}
