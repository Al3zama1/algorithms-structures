package com.abranlezama.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Bucket Sort Complexity

Time Complexity
Best	O(n+k)
Worst	O(n2)
Average	O(n)
Space Complexity	O(n+k)
Stability	Yes

Worst Case Complexity: O(n2)
When there are elements of close range in the array, they are likely to be placed in the same bucket. This may result in some buckets having more number of elements than others.
It makes the complexity depend on the sorting algorithm used to sort the elements of the bucket.
The complexity becomes even worse when the elements are in reverse order. If insertion sort is used to sort elements of the bucket, then the time complexity becomes O(n2).

Best Case Complexity: O(n+k)
It occurs when the elements are uniformly distributed in the buckets with a nearly equal number of elements in each bucket.
The complexity becomes even better if the elements inside the buckets are already sorted.
If insertion sort is used to sort elements of a bucket then the overall complexity in the best case will be linear ie. O(n+k).
O(n) is the complexity for making the buckets and O(k) is the complexity for sorting the elements of the bucket using algorithms having linear time complexity at the best case.

Average Case Complexity: O(n)
It occurs when the elements are distributed randomly in the array. Even if the elements are not distributed uniformly,
bucket sort runs in linear time. It holds true until the sum of the squares of the bucket sizes is linear in the total number of elements.

Bucket Sort Applications

Bucket sort is used when:
- input is uniformly distributed over a range.
- there are floating point values
 */

public class BuckedSort {
    public static void main(String[] args) {
        int[] arr = {4, 1, 0, 11, 44, 2, 8, 7};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        ArrayList<Integer>[] buckets = new ArrayList[arr.length + 1];
        int max = Arrays.stream(arr).max().getAsInt();

        for (int i = 0; i < arr.length; i++) {
            /*
            (number of elements)8 * (max value)44 / (max value)44
            arr.length + 1 buckets are needed because as shown above, the length of the array * the max value
            divided by the max value gives bucket 8. Index 8 falls outside a length 8 array, that is why we
            add 1 extra bucket
             */
            int bucket = (int) Math.floor(arr.length * arr[i] / max);
            if (buckets[bucket] == null) buckets[bucket] = new ArrayList<>();
            buckets[bucket].add(arr[i]);
        }

        // sort each bucket - usually is done with insertion sort
        for (List<Integer> bucket : buckets) {
            if (bucket == null) continue;
            Collections.sort(bucket);
        }

        int outputIndex = 0;
        for (List<Integer> bucket : buckets) {
            if (bucket == null) continue;
            for (int value : bucket) {
                arr[outputIndex++] = value;
            }
        }
    }
}
