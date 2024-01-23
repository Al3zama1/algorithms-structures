package com.abranlezama.algorithms.sort;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 3, 0, 10, 7, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int index = i;
            while (index > 0 && arr[index] < arr[index - 1]) {
                int temp = arr[index - 1];
                arr[index - 1] = arr[index];
                arr[index] = temp;
                index--;
            }
        }
    }
}
