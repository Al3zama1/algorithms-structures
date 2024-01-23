package com.abranlezama.algorithms.sort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {1, 5, 2, -3, 0, -5, 10, -1, 7, 6, 1221};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        Map<Boolean, List<Integer>> map = Arrays.stream(arr).boxed().collect(Collectors.partitioningBy(i -> i < 0));
        // split positive and negative values into two separate arrays
        int[] positiveArr = map.get(false).stream().mapToInt(Integer::intValue).toArray();
        int[] negativeArr = map.get(true).stream().mapToInt(Integer::intValue).map(Math::abs).toArray();

        radixSort(positiveArr);
        radixSort(negativeArr);

        for (int i = negativeArr.length - 1, j = 0; i >= 0; i--, j++) arr[j] = -negativeArr[i];
        System.arraycopy(positiveArr, 0, arr, negativeArr.length, positiveArr.length);
    }

    private static void radixSort(int[] arr) {
        // find out maximum number
        int max = Arrays.stream(arr).max().getAsInt();

        // the goal is to sort each number for its 1's, 10's, 100's, ... and so on value
        for (int exp = 1; max / exp > 0; exp *= 10) {
            sort(arr, exp);
        }
    }

    private static void sort(int[] arr, int exp) {
        int[] count = new int[10];

        for (int num : arr) count[(num / exp) % 10]++;

        for (int i = 1; i < 10; i++) count[i] += count[i - 1];

        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int num = arr[i];
            int index = count[(num / exp) % 10] - 1;
            output[index] = num;
            count[(num / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }
}
