package com.abranlezama.algorithms.sort;

import java.util.Arrays;


/*
Time Complexity
Best	O(n*log n)
Worst	O(n2)
Average	O(n*log n)
Space Complexity	O(log n)
Stability	No



1. Time Complexities
Worst Case Complexity [Big-O]: O(n2)
It occurs when the pivot element picked is either the greatest or the smallest element.

This condition leads to the case in which the pivot element lies in an extreme end of the sorted array.
One sub-array is always empty and another sub-array contains n - 1 elements.
Thus, quicksort is called only on this sub-array.

However, the quicksort algorithm has better performance for scattered pivots.
Best Case Complexity [Big-omega]: O(n*log n)
It occurs when the pivot element is always the middle element or near to the middle element.
Average Case Complexity [Big-theta]: O(n*log n)
It occurs when the above conditions do not occur.

2. Space Complexity
The space complexity for quicksort is O(log n).

Quicksort Applications
Quicksort algorithm is used when

- the programming language is good for recursion
- time complexity matters
- space complexity matters
 */

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2, 10, 8, 7};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString( arr));
    }

    private static void sort(int[] arr, int left, int right) {
        if (left >= right) return;

        int pivot = partition(arr, left, right);
        sort(arr, left,pivot - 1);
        sort(arr, pivot + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivotIndex = (left + right) / 2;
        swap(arr, pivotIndex, right);
        int pivotIndexCounter = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < arr[right]) {
                swap(arr, pivotIndexCounter, i);
                pivotIndexCounter++;
            }
        }
        swap(arr, pivotIndexCounter, right);
        return pivotIndexCounter;
    }

    private static void swap(int[] arr, int pivotIndex, int right) {
        if (pivotIndex != right) {
            int temp = arr[pivotIndex];
            arr[pivotIndex] = arr[right];
            arr[right] = temp;
        }
    }
}
