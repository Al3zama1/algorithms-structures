package com.abranlezama.algorithms.sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2, 10, 8, 7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int n = arr.length;

        // build max heap
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // sort
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int leftChild = i * 2 + 1;
        int rightChild = i * 2 + 2;

        // determine largest node(value) among left, middle, and right nodes.
        if (leftChild < n && arr[leftChild] > arr[largest]) largest = leftChild;
        if (rightChild < n && arr[rightChild] > arr[largest]) largest = rightChild;

        // keep heapifying if i was not the largest
        if (i != largest) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }
}
