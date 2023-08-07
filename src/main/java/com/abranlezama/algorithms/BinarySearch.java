package com.abranlezama.algorithms;

/*
Binary Search Complexity
        Time Complexities

        Best case complexity: O(1)
        Average case complexity: O(log n)
        Worst case complexity: O(log n)

        Space Complexity

        The space complexity of the binary search is O(1).

        Binary Search Applications
        In libraries of Java, .Net, C++ STL
        While debugging, the binary search is used to pinpoint the place where the error happens.
*/

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 7, 8, 10, 22, 30};

        System.out.println(search(arr, 22));
    }

    private static boolean search(int[] arr, int element) {
        int left = 0;
        int right = arr.length -1;


        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (element < arr[middle]) right = middle -1;
            else if (element > arr[middle]) left = middle +1;
            else return true;
        }

        return false;
    }

}
