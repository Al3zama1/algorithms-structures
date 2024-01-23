package com.abranlezama.algorithms.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 3, 0, 10, 7, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;

        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int middle = (left + right) / 2;

        swap(arr, middle, right);
        int finalPivotIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < arr[right]) {
                swap(arr, i, finalPivotIndex);
                finalPivotIndex++;
            }
        }
        swap(arr, finalPivotIndex, right);
        return finalPivotIndex;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
