package com.masanta.ratan.daily.practice.dsa.practice.geeksforgeeks;

import java.util.Arrays;

public class TripletFamily {

    /**
     * Problem statement:
     * Given an array arr of integers. Find whether three numbers are such that the sum of two elements equals the third element.
     *
     * Example:
     *
     * Input: arr[] = [1, 2, 3, 4, 5]
     * Output: true
     * Explanation: The pair (1, 2) sums to 3.
     * Input: arr[] = [5, 3, 4]
     * Output: false
     * Explanation: No triplets satisfy the condition.
     * Expected Time Complexity: O(n2)
     * Expected Auxilary Space: O(1)
     *
     * Constraints:
     * 1 <= arr.size() <= 103
     * 0 <= arr[i] <= 105
     */

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 5};
        System.out.println(STR."Triplet exists in \{Arrays.toString(arr)} : \{findTriplet(arr)}");
    }

    public static boolean findTriplet(int[] arr) {

        int n=arr.length;
        Arrays.sort(arr);
        for (int j : arr) {
            int left = 0, right = n - 1;
            while (left < right) {
                int sum = arr[left] + arr[right];
                if (sum == j) return true;
                else if (sum < j) left++;
                else right--;
            }
        }

        return false;

    }
}
