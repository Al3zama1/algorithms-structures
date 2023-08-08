package com.abranlezama.algorithms.sort;

/*
Hence, the number of comparisons is

        (n-1) + (n-2) + (n-3) +.....+ 1 = n(n-1)/2
        nearly equals to n2

        Hence, Complexity: O(n2)

        Also, if we observe the code, bubble sort requires two loops. Hence, the complexity is n*n = n2

        1. Time Complexities

        Worst Case Complexity: O(n2)
        If we want to sort in ascending order and the array is in descending order then the worst case occurs.

        Best Case Complexity: O(n)
        If the array is already sorted, then there is no need for sorting.

        Average Case Complexity: O(n2)
        It occurs when the elements of the array are in jumbled order (neither ascending nor descending).

        Bubble Sort Applications

        bubble sort is used if

        complexity does not matter
        short and simple code is preferred

*/

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {4, 1, 0, 11, 44, 2, 8, 7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - i -1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapped = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (!swapped) break;;
        }
    }

}
