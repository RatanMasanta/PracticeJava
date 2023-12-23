package com.masanta.ratan.daily.practice.coding.contests;

import java.util.Arrays;

public class FindPolygonWithTheLargestPerimeter {

    public static long largestPerimeter(int[] nums) {
        // Sort the numbers in ascending order
        Arrays.sort(nums);

        // Create a prefix sum array to store cumulative sums
        long[] prefixsum = new long[nums.length];
        prefixsum[0] = nums[0];  // Initialize the first prefix sum

        // Calculate prefix sums for each index
        for (int i = 1; i < nums.length; i++) {
            prefixsum[i] = nums[i] + prefixsum[i - 1];
        }

        // Variable to store the largest valid perimeter
        long ans = -1;

        // Iterate through the sorted numbers, starting from the largest
        for (int i = 2; i < nums.length; i++) {
            // Check if the current number satisfies the polygon condition
            if (nums[i] < prefixsum[i - 1]) {
                // A valid polygon is possible, update the answer
                ans = prefixsum[i];  // Perimeter is the sum of all elements up to nums[i]
            }
        }

        return ans;  // Return the largest valid perimeter or -1 if none found
    }

    public static void main(String[] args) {
        System.out.println(FindPolygonWithTheLargestPerimeter.largestPerimeter(new int[] {1,12,1,2,5,50,3}));
    }

}
