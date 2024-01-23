package com.abranlezama.algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 3, 0, 10, 7, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        List<Integer>[] buckets = new ArrayList[arr.length + 1];
        int max = Arrays.stream(arr).max().getAsInt();

        for (int num : arr) {
            int bucket = num * arr.length / max;
            if (buckets[bucket] == null) buckets[bucket] = new ArrayList<>();
            buckets[bucket].add(num);
        }

        for (List<Integer> bucket : buckets) {
            if (bucket == null) continue;
            Collections.sort(bucket);
        }

        int index = 0;
        for (List<Integer> bucket : buckets) {
            if (bucket == null) continue;
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }
}
