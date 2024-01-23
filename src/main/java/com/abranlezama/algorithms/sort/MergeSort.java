package com.abranlezama.algorithms.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 3, 0, 10, 7, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        mergeSort(arr, new int[arr.length], 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return; // there is only one value in left - right sub-section

        int middle = (left + right) / 2;
        mergeSort(arr, temp, left, middle);
        mergeSort(arr, temp, middle + 1, right);
        merge(arr, temp, left, right);
    }

    private static void merge(int[] arr, int[] temp, int left, int right) {
        int leftEnd = (left + right) / 2;
        int leftIndex = left;
        int rightIndex = leftEnd + 1;
        int tempIndex = left;

        while (leftIndex <= leftEnd && rightIndex <= right) {
            if (arr[leftIndex] < arr[rightIndex]) temp[tempIndex++] = arr[leftIndex++];
            else temp[tempIndex++] = arr[rightIndex++];
        }

        while (leftIndex <= leftEnd) temp[tempIndex++] = arr[leftIndex++];
        while (rightIndex <= right) temp[tempIndex++] = arr[rightIndex++];

        System.arraycopy(temp, left, arr, left, right - left + 1);
    }
}
