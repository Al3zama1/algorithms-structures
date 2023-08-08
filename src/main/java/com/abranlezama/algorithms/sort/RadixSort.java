package com.abranlezama.algorithms.sort;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {1012, 5221, 2212, 3234, 4753, -100, 3417, 1532, 1020, -2000};

        Map<Boolean, List<Integer>> splitArray = Arrays.stream(arr).boxed().collect(Collectors.partitioningBy(i -> i < 0));
        int[] negativeInts = radixSort(splitArray.get(true).stream().mapToInt(Integer::intValue).map(Math::abs).toArray());
        int[] positiveInts = radixSort(splitArray.get(false).stream().mapToInt(Integer::intValue).toArray());

        for (int i = negativeInts.length - 1, j = 0; i >= 0; i--, j++) arr[j] = -negativeInts[i];
        System.arraycopy(positiveInts, 0, arr, negativeInts.length, positiveInts.length);


        System.out.println(Arrays.toString(arr));
    }

    public static int[] radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(Integer.MAX_VALUE);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            sort(arr, exp);
        }
        return arr;
    }

    public static void sort(int[] arr, int exp) {
        int[] count = new int[10];
        for (int value : arr) count[(value / exp) % 10]++;

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int current = arr[i];
            int index = count[(current / exp) % 10] - 1;
            output[index] = current;
            count[(current / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }


}
