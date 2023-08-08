package com.abranlezama.algorithms.search;

public class LinearSearch {
    public static void main(String[] args) {
        int[] arr = {2, 1, 33, 4, 10};

        boolean found = search(arr, 4);
        System.out.println(found);
    }

    private static boolean search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return true;
        }
        return false;
    }
}
