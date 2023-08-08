package com.abranlezama.algorithms.sort;

import java.util.Arrays;

/*
Worst Case Complexity: O(n2)
Suppose, an array is in ascending order, and you want to sort it in descending order. In this case, worst case complexity occurs.

Each element has to be compared with each of the other elements so, for every nth element, (n-1) number of comparisons are made.
Thus, the total number of comparisons = n*(n-1) ~ n2

Best Case Complexity: O(n)
When the array is already sorted, the outer loop runs for n number of times whereas the inner loop does not run at all.
So, there are only n number of comparisons. Thus, complexity is linear.

Average Case Complexity: O(n2)
It occurs when the elements of an array are in jumbled order (neither ascending nor descending).

Insertion Sort Applications

The insertion sort is used when:
- the array has a small number of elements
- there are only a few elements left to be sorted
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2, 10, 8, 7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            int index = i;
            while (index > 0 && arr[index] < arr[index - 1]) {
                int temp = arr[index - 1];
                arr[index - 1] = arr[index];
                arr[index] = temp;
                index--;
            }
        }
    }
}
