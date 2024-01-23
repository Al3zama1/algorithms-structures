package com.abranlezama.algorithms.sort;

import java.util.Arrays;

public class CountSort {
    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 3, 0, 10, 7, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int min = Arrays.stream(arr).min().orElse(Integer.MIN_VALUE);
        int max = Arrays.stream(arr).max().orElse(Integer.MAX_VALUE);
        int[] count = new int[max - min + 1];

        for (int num : arr) {
            count[num - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[arr.length];
        for (int num : arr) {
            int index = count[num - min] - 1;
            output[index] = num;
            count[num - min]--;
        }

        System.arraycopy(output, 0, arr, 0, output.length);
    }
}
