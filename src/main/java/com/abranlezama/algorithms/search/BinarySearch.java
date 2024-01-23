package com.abranlezama.algorithms.search;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 6, 7, 8, 10};
        boolean exists = contains(arr, 8);
        System.out.println(exists);
    }

    public static boolean contains(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int middle = (left + right) / 2;

            if (arr[middle] > target) right = middle - 1;
            else if (arr[middle] < target) left = middle + 1;
            else return true;
        }

        return false;
    }
}
