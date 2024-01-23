package com.abranlezama.algorithms.sort;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 3, 0, 10, 7, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int smallest = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[smallest]) smallest = j;
            }

            if (smallest != i) {
                int temp = arr[smallest];
                arr[smallest] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
