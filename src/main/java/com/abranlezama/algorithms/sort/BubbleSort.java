package com.abranlezama.algorithms.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 3, 0, 10, 7, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean swaps = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    swaps = true;
                }
            }

            if (!swaps) break;
        }
    }
}
