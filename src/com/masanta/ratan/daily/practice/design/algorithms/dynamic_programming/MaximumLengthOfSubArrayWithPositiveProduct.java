package com.masanta.ratan.daily.practice.design.algorithms.dynamic_programming;

public class MaximumLengthOfSubArrayWithPositiveProduct {

    /**
     *
     * 1567. Maximum Length of Subarray With Positive Product
     *
     * Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.
     *
     * A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
     *
     * Return the maximum length of a subarray with positive product.
     *
     * Example 1:
     *
     * Input: nums = [1,-2,-3,4]
     * Output: 4
     * Explanation: The array nums already has a positive product of 24.
     * Example 2:
     *
     * Input: nums = [0,1,-2,-3,-4]
     * Output: 3
     * Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
     * Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
     *
     * @param nums integer array provided
     * @return maximum length of sub array with positive product
     */
    public int getMaxLen(int[] nums) {
        // llp = Length of the longest positive subarray
        int llp = 0;
        // lln = length of the longest negative subarray
        int lln = 0;
        // max length of the positive subarray
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            // Reset the lengths upon encountering a zero
            if (nums[i] == 0) {
                llp = lln = 0;
            } else if (nums[i] > 0) {
                // Upon encountering a positive value, increase the length of the positive subarray till now by one
                llp++;
                // Increase the length of the negative subarray only if there's a negative product.
                // If there are no negative numbers before the current number or if the (i-1)th number is zero, we should not increase this value.
                if (lln > 0) lln++;
            } else {
                // Upon encountering a negative value, the lengths of the positive and negative subarrays need to be swapped.
                int temp = llp;
                // If there are no negative numbers before the current number, then llp should be zero.
                // so we need an extra check to ensure that
                llp = (lln > 0 ? lln + 1 : 0);
                lln = temp + 1;
            }
            max = Math.max(max, llp);
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumLengthOfSubArrayWithPositiveProduct maximumLengthOfSubArrayWithPositiveProduct =
            new MaximumLengthOfSubArrayWithPositiveProduct();
        int[] nums = {-1,-2,-3,0,1};
        System.out.println(maximumLengthOfSubArrayWithPositiveProduct.getMaxLen(nums));
    }

}
