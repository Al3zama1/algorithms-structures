package com.abranlezama.algorithms.sort;

import java.util.Arrays;

/*
Time Complexity
Best Case Complexity: O(n*log n)

Worst Case Complexity: O(n*log n)

Average Case Complexity: O(n*log n)

Space Complexity
The space complexity of merge sort is O(n).

Merge Sort Applications
Inversion count problem
External sorting
E-commerce applications
*/
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 44, 22, 11, 7};
        mergeSort(arr, new int[arr.length], 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return;

        int middle = (left + right) / 2;
        mergeSort(arr, temp, left, middle);
        mergeSort(arr, temp, middle + 1, right);
        merge(arr, temp, left, right);
    }

    private static void merge(int[] arr, int[] temp, int leftStart, int rightEnd) {
        int leftEnd = (leftStart + rightEnd) / 2;
        int rightStart = leftEnd + 1;

        int leftIndex = leftStart;
        int rightIndex = rightStart;
        int tempIndex = leftStart;

        while (leftIndex <= leftEnd && rightIndex <= rightEnd) {
            if (arr[leftIndex] < arr[rightIndex]) temp[tempIndex++] = arr[leftIndex++];
            else temp[tempIndex++] = arr[rightIndex++];
        }

        while (leftIndex <= leftEnd) temp[tempIndex++] = arr[leftIndex++];
        while (rightIndex <= rightEnd) temp[tempIndex++] = arr[rightIndex++];

        System.arraycopy(temp, leftStart, arr, leftStart, rightEnd + 1 - leftStart);
    }

}
