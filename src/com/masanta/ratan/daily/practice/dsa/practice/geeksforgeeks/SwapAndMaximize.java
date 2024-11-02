package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import java.util.Arrays;

public class SwapAndMaximize {

    /**
     *
     * Problem statement:
     *
     * Given an array arr[ ] of positive elements. Consider the array as a circular array, meaning the element after the last element is the first element of the array. The task is to find the maximum sum of the absolute differences between consecutive elements with shuffling of array elements allowed i.e. shuffle the array elements and make [a1..an] such order that  |a1 – a2| + |a2 – a3| + …… + |an-1 – an| + |an – a1| is maximized.
     *
     * Examples:
     *
     * Input: arr[] = [4, 2, 1, 8]
     * Output: 18
     * Explanation: After Shuffling, we get [1, 8, 2, 4]. Sum of absolute difference between consecutive elements after rearrangement = |1 - 8| + |8 - 2| + |2 - 4| + |4 - 1| = 7 + 6 + 2 + 3 = 18.
     * Input: arr[] = [10, 12]
     * Output: 4
     * Explanation: No need of rearrangement. Sum of absolute difference between consecutive elements = |10 - 12| + |12 - 10| = 2 + 2 = 4.
     * Constraints:
     * 2 ≤ arr.size()≤ 105
     * 1 <= arr[i] <= 105
     *
     */

    void main() {
        Long[] arr = new Long[] {4L, 2L, 1L, 8L};
        System.out.println(STR."After swapping and maximizing the array \{Arrays.toString(arr)}, we get \{maxSum(arr)}");

    }


    /**
     *
     *
     *
     * @param arr
     * @return
     */
    private static long maxSum(Long[] arr) {
        // code here
        Arrays.sort(arr);
        int n = arr.length;
        Long[] rearranged = new Long[n];

        // Two pointers for rearranging
        int left = 0;
        int right = n - 1;

        // Fill rearranged array
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                rearranged[i] = arr[left++];
            } else {
                rearranged[i] = arr[right--];
            }
        }

        // Calculate maximum sum of absolute differences
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.abs(rearranged[i] - rearranged[(i + 1) % n]);
        }

        return sum;
    }


}
