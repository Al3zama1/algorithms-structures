package com.abranlezama.algorithms;

import java.lang.reflect.Array;
import java.util.Arrays;

/*
Overall complexity = O(max)+O(size)+O(max)+O(size) = O(max+size)

Worst Case Complexity: O(n+k)
Best Case Complexity: O(n+k)
Average Case Complexity: O(n+k)
In all the above cases, the complexity is the same because no matter how the elements are placed in the array, the algorithm goes through n+k times.

There is no comparison between any elements, so it is better than comparison based sorting techniques.
But, it is bad if the integers are very large because the array of that size should be made.
-eg: {1, 1002, 300} --> bad since we would have to do 1002 iterations just to sort 3 numbers.

Space Complexity

The space complexity of Counting Sort is O(max). Larger the range of elements, larger is the space complexity.

Counting Sort Applications

Counting sort is used when:
- there are smaller integers with multiple counts.
- linear complexity is the need.
*/
public class CountSort {
    public static void main(String[] args) {
        int[] arr = { 4, 2, 2, 8, 3, 3, 1 };
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

        int index = 0;
        /*
        - to sort in ascending order, flip the order of for loop.
        - (int i = count.length - 1; i >= 0; i--)
         */
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[index++] = i + min;
                count[i]--;
            }
        }
    }
}
