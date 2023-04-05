package com.masanta.ratan.daily.practice.leetcode.medium;

public class MinimizeMaximumOfArray {

    /**
     * 2439. Minimize Maximum of Array
     * You are given a 0-indexed array nums comprising of n non-negative integers.
     *
     * In one operation, you must:
     *
     * Choose an integer i such that 1 <= i < n and nums[i] > 0.
     * Decrease nums[i] by 1.
     * Increase nums[i - 1] by 1.
     * Return the minimum possible value of the maximum integer of nums after performing any number of operations.
     *
     *
     *
     * Approach:
     * Initialize total_sum and result to 0.
     * Loop through the nums array from index 0 to len(nums)-1.
     * For each index, add the value at that index to the total_sum.
     * Calculate the maximum of the current result and the division of total_sum and index+1 using integer division //.
     * Update the result with the maximum value.
     * Repeat steps 3-5 for all indices.
     * Return the result as the minimum maximum of the array.
     * Intuition:
     * The goal is to minimize the maximum of the array by dividing the array into subarrays and taking the sum of each subarray.
     * By dividing the array into subarrays and taking their sum, we can obtain the average value of each subarray.
     * The task is to find the maximum average value among all possible subarrays.
     * We can achieve this by iteratively calculating the sum of the array up to each index and dividing it by the number of elements up to that index.
     * We keep track of the maximum average value found so far and update it as we iterate through the array.
     * Finally, we return the maximum average value as the result, which represents the minimum maximum of the array. This is because a smaller average value indicates a smaller maximum value. By choosing the maximum average value, we are minimizing the maximum of the array.
     *
     *
     *
     * @param A
     * @return
     */
    public int minimizeArrayValue(int[] A) {
        long sum = 0, res = 0;
        for (int i = 0; i < A.length; ++i) {
            sum += A[i];
            res = Math.max(res, (sum + i) / (i + 1));
        }
        return (int)res;
    }


    public int minimizeArrayValueUsingPrefixSum(int[] nums) {
        // Initialize answer and the prefix sum.
        long answer = 0, prefixSum = 0;

        // Iterate over nums, update prefix sum and answer.
        for (int i = 0; i < nums.length; ++i) {
            prefixSum += nums[i];
            answer = Math.max(answer, (prefixSum + i) / (i + 1));
        }

        return (int)answer;
    }

    public static void main(String[] args) {
        MinimizeMaximumOfArray minimizeMaximumOfArray = new MinimizeMaximumOfArray();
        int[] numsArray = {3,7,1,6};
        System.out.println(minimizeMaximumOfArray.minimizeArrayValue(numsArray));
        System.out.println(minimizeMaximumOfArray.minimizeArrayValueUsingPrefixSum(numsArray));
    }


    /*
    Leetcode discuss: https://leetcode.com/problems/minimize-maximum-of-array/solutions/3381323/image-explanation-brute-better-o-nlogm-optimal-o-n-c-java-python/
    Leetcode discuss: https://leetcode.com/problems/minimize-maximum-of-array/solutions/3381227/python-java-c-simple-solution-easy-to-understand/
    Leetcode editorial: https://leetcode.com/problems/minimize-maximum-of-array/editorial/

     */

}
