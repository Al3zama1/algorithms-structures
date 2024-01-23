package com.abranlezama.algorithms.search;

public class LinearSearch {
    public static void main(String[] args) {
        int[] arr = {3, 9, 1, 0, 7};
        boolean exists = contains(arr, 7);
        System.out.println(exists);
    }

    public static boolean contains(int[] arr, int target) {
        for (int number : arr) {
            if (number == target) return true;
        }

        return false;
    }
}
