package com.abranlezama.algorithms;

import java.util.Arrays;

public class CountSortV2 {
    public static void main(String[] args) {
        int[] arr = { 4, 5, 2, -2, 2, 7 };
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        // find min and max to establish range of count[]
        int min = Arrays.stream(arr).min().getAsInt();
        int max = Arrays.stream(arr).max().getAsInt();

        /*
        - create an array that will contain the count of each element in the range max - min
        - eg: min = 1, max = 8 --> 8 - 1 + 1 (+1 because we need to take account of value 0)
         */
        int[] count = new int[max - min + 1];

        // map elements from arr[] to count[], value - min --> slot index
        for (int value : arr) count[value - min]++;

        // add previous count to all elements, will result on elements final position
        for (int i = 1; i < count.length; i++) count[i] += count[i - 1];


        int[] output = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];
            int index = count[current - min] - 1;
            output[index] = current;
            count[current - min]--;
        }

        for (int i = 0; i < arr.length; i++) arr[i] = output[i];
    }
}
